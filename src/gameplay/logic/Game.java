package gameplay.logic;

import gameplay.grid.*;
import gameplay.logic.exception.EquivalentPlayerNamesException;
import gameplay.logic.gridfactory.GridFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Game {
    protected ArrayList<Player> players;
    protected HashMap<String,Integer> playerScores;
    protected ArrayList<Monster> monsters;
    protected ArrayList<Bomb> bombs;
    protected Grid grid;

    private int level;

    final int gridLength = 5;
    final int gridWidth = 9;

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

//        grid.printGrid();
        populateGridWithPlayers();
        populateGridWithMonsters();
    }

    /**
     * places the (maximum) 4 player in the corners of the grid
     */
    private void populateGridWithPlayers(){
        ArrayList<Position> corners = new ArrayList<>(
            List.of(new Position(1,1), new Position(1,gridWidth), new Position(gridLength, 1), new Position(gridLength, gridWidth))
        );

        players = new ArrayList<>();
        int i=0;
        for (String name : playerScores.keySet()){
            players.add(new Player(name,corners.get(i),Direction.EAST));
            grid.addCharacter(players.get(i));
            i++;
        }

        // for debugging
//        grid.printGrid();
    }

    /**
     * places a level-dependent amount of monsters randomly on the grid
     */
    private void populateGridWithMonsters(){
        Random randomGenerator = new Random();
        boolean placedOne;
        boolean tooClose;

        monsters = new ArrayList<>();

        for(int monsterCount=0; monsterCount < level*2; monsterCount++){ // 2*N monsters on level N
            placedOne = false;

            while(!placedOne){
                Position pick = new Position(1+randomGenerator.nextInt(gridLength),1+randomGenerator.nextInt(gridWidth)); // random field on the grid
                tooClose = false;

                if(grid.getElement(pick).getType() == ElementType.EMPTY){ // if this field is empty
                    for(Player player : players) if (Grid.manhattanDistance(player.getPosition(),pick) < 4){ // and it isn't too close to any of the players
                            tooClose = true;
                            break;
                        }
                    if(!tooClose){
                        monsters.add(new Monster(monsterCount,pick,Direction.NORTH)); // then add monster
                        grid.addCharacter(monsters.get(monsterCount));
                        placedOne = true;
                    }
                }
            }

        }

        // for debugging
//        grid.printGrid();
    }


    private void run(){
        isRunning = true;
        while(isRunning){
            initializeNextLevel();




            isRunning = false; // debug
        }


    }

    /**
     * Print the current content of the grid
     */
    public void snapShot(){
        grid.printGrid();
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
