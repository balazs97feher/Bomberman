package gameplay.logic;

import gameplay.grid.Position;
import gameplay.logic.event.GameEvent;
import gameplay.logic.event.MonsterMovedEvent;

import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Controller implements Runnable{
    private HashMap<String,Integer> playerScores;
    private Level level;

    private ConcurrentLinkedQueue<GameEvent> eventPump;
    private ConcurrentLinkedQueue<GameEvent> eventSink;



    public Controller(HashMap<String,Integer> scoreBoard, Level l, ConcurrentLinkedQueue<GameEvent> pump, ConcurrentLinkedQueue<GameEvent> sink){
        playerScores = scoreBoard;
        level = l;
        eventPump = pump;
        eventSink = sink;
    }

    @Override
    public void run() {
        System.out.println("level started");


    }


}
