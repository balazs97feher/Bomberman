package gameplay.logic;

import gameplay.grid.*;
import gameplay.logic.event.GameEvent;
import gameplay.logic.event.GameEventFactory;
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
        System.out.println("level started");
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new HandleMonsters(this),0,1000);
        timer.scheduleAtFixedRate(new HandleEventSink(this),0,1000);





        while(true){}
//        timer.cancel();
//        System.out.println("level completed");
//        gameFlag.isRunning = false;
    }

    public void handleMonsters(){
        System.out.println("handling monsters");

        for (Monster monster : level.monsters) {
            moveMonster(monster);
        }
        level.grid.printGrid();

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

            eventPump.add(eventFactory.createMonsterMovedEvent(monster.getId(),monster.getPosition(),neighbor.getPosition()));

            Position monsterPosition = monster.getPosition();
            level.grid.setElement(neighbor.getPosition(), monster);
            monster.setDirection(setDirection);
            level.grid.setElement(monsterPosition, neighbor);
        }
    }






    public void handleEventSink(){
//        System.out.println("handling event sink");
        GameEvent next = eventSink.poll();
        if(next != null){
            System.out.println("outside event handled");
        }
    }


}
