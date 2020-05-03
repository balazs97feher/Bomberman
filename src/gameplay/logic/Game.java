package gameplay.logic;

import gameplay.grid.*;
import gameplay.logic.exception.EquivalentPlayerNamesException;
import gameplay.logic.gridfactory.GridFactory;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    protected ArrayList<Player> players;
    protected HashMap<String,Integer> playerScores;
    protected ArrayList<Monster> monsters;
    protected ArrayList<Bomb> bombs;
    protected Grid grid;

    private int level;

    final int gridLength = 15;
    final int gridWidth = 21;

    private GridFactory gridFactory;
    private Controller controller;
    private boolean isRunning;

    public Game(){
        gridFactory = new GridFactory(gridLength, gridWidth);
        controller = new Controller(this);
        level = 0;
        isRunning = false;
    }

    /**
     * should be called right after a game is constructed
     * @param playerNames non-empty list of the names of the players
     * @throws EquivalentPlayerNamesException
     */
    public void initialize(ArrayList<String> playerNames) throws EquivalentPlayerNamesException {
        playerScores = new HashMap<>(playerNames.size());
        for (String name : playerNames){
            if(playerScores.containsKey(name)) throw new EquivalentPlayerNamesException("There is a player called " + name + " already!");
            else playerScores.put(name, 0);
        }

        run();
    }

    private void initializeNextLevel(){
        level++;
        grid = gridFactory.makeUniformGrid();

        populateGridWithPlayers();
        populateGridWithMonsters();
    }

    private void populateGridWithPlayers(){

    }

    private void populateGridWithMonsters(){

    }


    private void run(){
        isRunning = true;
        while(isRunning){
            initializeNextLevel();





        }


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
