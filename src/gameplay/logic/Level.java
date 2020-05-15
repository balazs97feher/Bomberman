package gameplay.logic;

import gameplay.grid.Bomb;
import gameplay.grid.Grid;
import gameplay.grid.Monster;
import gameplay.grid.Player;

import java.util.ArrayList;

public class Level {
    protected ArrayList<Player> players;
    protected ArrayList<Monster> monsters;
    protected ArrayList<Bomb> bombs;
    protected Grid grid;

    protected int levelNumber;

    protected Level(int levelNo, Grid g){
        levelNumber = levelNo;
        grid = g;
    }





}
