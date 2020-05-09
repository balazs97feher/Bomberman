package gameplay.logic;

import gameplay.grid.ElementType;
import gameplay.grid.GridElement;
import gameplay.grid.Monster;
import gameplay.grid.Position;
import gameplay.logic.event.GameEvent;
import gameplay.logic.event.MonsterMovedEvent;
import gameplay.logic.schedule.HandleEventSink;
import gameplay.logic.schedule.HandleMonsters;

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



    public Controller(HashMap<String,Integer> scoreBoard, Level l, ConcurrentLinkedQueue<GameEvent> pump, ConcurrentLinkedQueue<GameEvent> sink, GameFlag flag){
        playerScores = scoreBoard;
        level = l;
        eventPump = pump;
        eventSink = sink;
        gameFlag = flag;
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

        Random randomGenerator = new Random();
        for (Monster monster : level.monsters) {
            moveMonster(monster);
        }


    }

    private void moveMonster(Monster monster){
        GridElement neighbor = level.grid.getNeighbor(monster.getPosition(),monster.getDirection());

        if(neighbor != null){
            if(neighbor.getType() == ElementType.EMPTY){
                eventPump.add(new MonsterMovedEvent(monster.getId(),monster.getPosition(),neighbor.getPosition()));
                Position monsterPosition = monster.getPosition();
                level.grid.setElement(monster,neighbor.getPosition());
                level.grid.setElement(neighbor,monsterPosition);
                level.grid.printGrid();
            }
            else if(neighbor.getType() == ElementType.WALL){

            }
        }
    }






    public void handleEventSink(){
//        System.out.println("handling event sink");
    }


}
