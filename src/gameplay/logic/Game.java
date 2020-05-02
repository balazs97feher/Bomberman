package gameplay.logic;

import gameplay.grid.*;
import gameplay.logic.gridfactory.GridFactory;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    protected ArrayList<Player> players;
    protected HashMap<String,Integer> playerScores;
    protected ArrayList<Monster> monsters;
    protected ArrayList<Bomb> bombs;
    protected Grid grid;

    final int gridLength = 15;
    final int gridWidth = 21;

    private GridFactory gridFactory;
    private Controller controller;

    public Game(){
        gridFactory = new GridFactory(gridLength, gridWidth);
        controller = new Controller(this);
    }

    public void initialize(ArrayList<String> players){

    }



    public void movePlayer(String name, Direction dir){
        // TODO
    }

    public void placeBomb(String name){
        // TODO
    }



}
