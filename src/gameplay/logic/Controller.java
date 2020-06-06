package gameplay.logic;

import gameplay.LoggerMan;
import gameplay.grid.*;
import gameplay.logic.event.GameEvent;
import gameplay.logic.event.GameEventFactory;
import gameplay.logic.event.MovePlayerEvent;
import gameplay.logic.event.PlaceBombEvent;
import gameplay.logic.schedule.Detonation;
import gameplay.logic.schedule.HandleEventSink;
import gameplay.logic.schedule.HandleMonsters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.ConcurrentLinkedQueue;


public class Controller implements Runnable{
    private HashMap<String,Integer> playerScores;
    private Level level;

    private ConcurrentLinkedQueue<GameEvent> eventPump;
    private ConcurrentLinkedQueue<GameEvent> eventSink;

    private Timer timer;

    private GameFlag gameFlag;
    private GameEventFactory eventFactory;


    public Controller(HashMap<String,Integer> scoreBoard, Level l, ConcurrentLinkedQueue<GameEvent> pump, ConcurrentLinkedQueue<GameEvent> sink, GameFlag flag){
        playerScores = scoreBoard;
        level = l;
        eventPump = pump;
        eventSink = sink;
        gameFlag = flag;
        eventFactory = new GameEventFactory();
    }

    @Override
    public void run() {
        LoggerMan.log(java.util.logging.Level.INFO, "Level " + level.levelNumber + " has started.");
        timer = new Timer();
        timer.scheduleAtFixedRate(new HandleMonsters(this),0,800);
        timer.scheduleAtFixedRate(new HandleEventSink(this),500,100);

        while(!gameFlag.terminated){}
        timer.cancel();
        System.out.println("level completed");
        gameFlag.running = false;
    }

    public void handleMonsters(){
        for (Monster monster : level.monsters) {
            synchronized (level){
                moveMonster(monster);
            }
        }
    }

    private void moveMonster(Monster monster){
        ArrayList<Direction> freeDirections = new ArrayList<>(); // collect all the possible directions a monster can go into
        for (Direction direction : Grid.directions){
            GridElement neighbor = level.grid.getNeighbor(monster.getPosition(),direction);
            if(neighbor != null && neighbor.getType() == ElementType.EMPTY) freeDirections.add(direction);
        }

        Direction setDirection;
        Random randomGenerator = new Random();

        Direction opposite = Grid.oppositeDirections.get(monster.getDirection());
        // reduce chances of turning back when no need to
        if(freeDirections.contains(opposite) && freeDirections.size() > 1 && randomGenerator.nextInt(10)<10) freeDirections.remove(opposite);

        if(freeDirections.size() != 0){
            if(freeDirections.contains(monster.getDirection())){
                if(freeDirections.size() > 1){
                    if(randomGenerator.nextInt(4)<3) setDirection = monster.getDirection(); // 75% chance of going ahead, if it is free
                    else{
                        freeDirections.remove(monster.getDirection());
                        setDirection = freeDirections.get(randomGenerator.nextInt(freeDirections.size())); // if not ahead, then turn randomly
                    }
                }
                else setDirection = monster.getDirection();
            }
            else setDirection = freeDirections.get(randomGenerator.nextInt(freeDirections.size())); // if cannot go ahead, then turn randomly
            GridElement neighbor = level.grid.getNeighbor(monster.getPosition(),setDirection);

            eventPump.add(eventFactory.createMonsterMovedEvent(monster.getId(),monster.getPosition(),neighbor.getPosition(),setDirection));

            level.grid.swapElements(monster,neighbor);
            monster.setDirection(setDirection);
        }
        killNearbyPlayers(monster);
    }

    public void handleEventSink() {
        GameEvent next = eventSink.poll();
        if(next != null){
            synchronized (level){
                switch (next.getEventType()){
                    case MOVE_PLAYER:
                        MovePlayerEvent e1 = (MovePlayerEvent)next;
                        movePlayer(e1.getPlayerName(),e1.getDirection());
                        break;
                    case PLACE_BOMB:
                        PlaceBombEvent e2 = (PlaceBombEvent) next;
                        placeBomb(e2.getPlayerName());
                        break;
                    default:
                        LoggerMan.log(java.util.logging.Level.WARNING, "Unable to handle event from event sink.");
                }
            }
        }
    }

    private void movePlayer(String playerName, Direction direction){
        Player player = findPlayer(playerName);

        if(player == null){
            String noSuchPlayer = "No such player in this level.";
            LoggerMan.log(java.util.logging.Level.WARNING,"movePlayer: " + noSuchPlayer);
        }
        else{
            GridElement neighbor = level.grid.getNeighbor(player.getPosition(),direction);
            if(neighbor != null && neighbor.getType() == ElementType.EMPTY){
                eventPump.add(eventFactory.createPlayerMovedEvent(player.getId(),player.getPosition(),neighbor.getPosition(),direction));

                level.grid.swapElements(player,neighbor);
                player.setDirection(direction);
            }
            else{
                LoggerMan.log(java.util.logging.Level.INFO, "Player: " + player.getName() + " cannot move in direction: "
                    + direction.toString() + " from " + player.getPosition().toString());
            }
        }
    }

    private void placeBomb(String playerName){
        Player player = findPlayer(playerName);

        if(player == null){
            String noSuchPlayer = "No such player in this level.";
            LoggerMan.log(java.util.logging.Level.SEVERE,"placeBomb: " + noSuchPlayer);
            throw new NullPointerException(noSuchPlayer);
        }
        else{
            Direction stepAway = null;
            GridElement neighbor = level.grid.getNeighbor(player.getPosition(),player.getDirection());
            if(neighbor != null && neighbor.getType() == ElementType.EMPTY){
                stepAway = player.getDirection(); // if possible, place bomb and step away in the player's original direction
            }
            else{
                for(Direction direction : Grid.directions){ // if not, find a direction to step away to
                    neighbor = level.grid.getNeighbor(player.getPosition(),direction);
                    if(neighbor != null && neighbor.getType() == ElementType.EMPTY){
                        stepAway = direction;
                        break;
                    }
                }
            }
            if(stepAway == null){
                LoggerMan.log(java.util.logging.Level.SEVERE,"placeBomb: Player is stuck, cannot place bomb.");
            }
            else{
                Bomb bomb = new Bomb(player.getPosition(), player);
                level.bombs.add(bomb);
                level.grid.swapElements(player,neighbor);
                player.setDirection(stepAway);
                level.grid.setElement(neighbor.getPosition(),bomb);
                eventPump.add(eventFactory.createBombPlacedEvent(bomb.getPosition(), bomb.getId()));
                eventPump.add(eventFactory.createPlayerMovedEvent(player.getId(),neighbor.getPosition(),player.getPosition(),stepAway));
                timer.schedule(new Detonation(this, bomb), bomb.getDetonationTime());
            }
        }
    }

    public void detonateBomb(Bomb bomb){
        level.bombs.remove(bomb);
        eventPump.add(eventFactory.createBombDetonatedEvent(bomb.getId()));
        level.grid.setElement(bomb.getPosition(),new EmptyElement(bomb.getPosition()));
    }

    private void killNearbyPlayers(Monster monster){
        for(Direction direction : Grid.directions){
            GridElement neighbor = level.grid.getNeighbor(monster.getPosition(),direction);
            if(neighbor != null && neighbor.getType() == ElementType.PLAYER){
                Player playerToKill = (Player)neighbor;
                level.players.remove(playerToKill);
                level.grid.setElement(playerToKill.getPosition(),new EmptyElement(playerToKill.getPosition()));
                eventPump.add(eventFactory.createPlayerKilledEvent(playerToKill.getId()));
            }
        }
    }

    private Player findPlayer(String name){
        Player player = null;
        for(Player p : level.players) if(p.getName().equals(name)) {
            player = p;
            break;
        }
        return player;
    }

}
