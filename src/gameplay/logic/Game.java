package gameplay.logic;

import gameplay.grid.*;
import gameplay.logic.event.GameEvent;
import gameplay.logic.exception.EquivalentPlayerNamesException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Game {
    protected HashMap<String,Integer> playerScores;

    private LevelFactory levelFactory;
    protected Level level;
    protected int levelNumber;

    final private int gridLength = 5;
    final private int gridWidth = 9;

    private Controller controller;
    private boolean isRunning;

    public ConcurrentLinkedQueue<GameEvent> eventPump;
    public ConcurrentLinkedQueue<GameEvent> eventSink;


    public Game(){
        levelNumber = 0;
        isRunning = false;
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

    public void initializeNextLevel(){
        levelNumber++;
        level = levelFactory.makeLevel(levelNumber);

        eventPump.clear();
        eventSink.clear();

        level.grid.printGrid();
    }

    public void startLevel(){
            controller = new Controller(playerScores, level, eventPump, eventSink);
            Thread levelThread = new Thread(controller);
            levelThread.start();

    }






    /**
     * Print the current content of the grid
     */
    public void snapShot(){
        level.grid.printGrid();
    }

    /**
     * function to move a player on the grid
     * @param name name of the player to  move
     * @param dir in which direction to move
     */
    public void movePlayer(String name, Direction dir){
        // TODO
    }

    /**
     * function to place a bomb where the player currently is
     * @param name name of the player to place the bomb
     */
    public void placeBomb(String name){
        // TODO
    }



}
