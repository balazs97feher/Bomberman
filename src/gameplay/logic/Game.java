package gameplay.logic;

import gameplay.LoggerMan;
import gameplay.grid.*;
import gameplay.logic.event.GameEvent;
import gameplay.logic.event.MovePlayerEvent;
import gameplay.logic.event.PlaceBombEvent;
import gameplay.logic.exception.EquivalentPlayerNamesException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Game {
    protected HashMap<String,Integer> playerScores;

    private LevelFactory levelFactory;
    private Level level;
    private int levelNumber;

    final private int gridLength = 9;
    final private int gridWidth = 15;

    private Controller controller;
    private GameFlag gameFlag; // flag that indicates that a level is in progress

    private ConcurrentLinkedQueue<GameEvent> eventPump;
    private ConcurrentLinkedQueue<GameEvent> eventSink;


    public Game(){
        levelNumber = 0;
        gameFlag = new GameFlag();
    }

    public boolean isRunning(){
        return (gameFlag.running || !eventPump.isEmpty());
    }

    /**
     * should be called right after a game is constructed
     * @param playerNames non-empty list of the names of the players
     * @throws EquivalentPlayerNamesException every player has to have a different name
     */
    public void initialize(ArrayList<String> playerNames) throws EquivalentPlayerNamesException {
        playerScores = new HashMap<>(playerNames.size());

        for (String name : playerNames){
            if(playerScores.containsKey(name)) throw new EquivalentPlayerNamesException("There is a player called " + name + " already!");
            else playerScores.put(name, 0);
        }

        levelFactory = new LevelFactory(new ArrayList<>(playerScores.keySet()),gridLength,gridWidth);
        eventPump = new ConcurrentLinkedQueue<>();
        eventSink = new ConcurrentLinkedQueue<>();
    }

    /**
     * initialize the next level and return the initial state of the grid for other subsystems (e.g. UI)
     * @return
     */
    public ArrayList<ArrayList<GridElement>> initializeNextLevel(){
        levelNumber++;
        level = levelFactory.makeLevel(levelNumber);

        eventPump.clear();
        eventSink.clear();

        return level.grid.getElements();
    }

    /**
     * set the running game flag to true and start the level
     * after this, expect events to appear in the event pump
     */
    public void startLevel(){
            controller = new Controller(playerScores, level, eventPump, eventSink, gameFlag);
            Thread levelThread = new Thread(controller);
            gameFlag.running = true;
            levelThread.start();
    }

    public GameEvent pollEventPump(){
        return eventPump.poll();
    }

    public void terminate(){
        gameFlag.terminated = true;
        LoggerMan.log(java.util.logging.Level.WARNING,"Game has been terminated.");
    }


    public int getGridLength(){
        return gridLength;
    }

    public int getGridWidth(){
        return gridWidth;
    }

    /**
     * function to request to move a player on the grid
     * @param name name of the player to  move
     * @param dir in which direction to move
     */
    public void movePlayer(String name, Direction dir){
        MovePlayerEvent e = new MovePlayerEvent(name,dir);
        eventSink.add(e);
        LoggerMan.log(java.util.logging.Level.INFO,e.toString());
    }

    /**
     * function to place a bomb where the player currently is
     * @param name name of the player to place the bomb
     */
    public void placeBomb(String name){
        PlaceBombEvent e = new PlaceBombEvent(name);
        eventSink.add(e);
        LoggerMan.log(java.util.logging.Level.INFO,e.toString());
    }



}
