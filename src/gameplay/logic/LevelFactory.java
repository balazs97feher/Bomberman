package gameplay.logic;

import gameplay.grid.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelFactory {
    private ArrayList<String> playerNames;
    private GridFactory gridFactory;
    private int gridLength;
    private int gridWidth;

    public LevelFactory(ArrayList<String> names, int gridL, int gridW){
        playerNames = names;
        gridFactory = new GridFactory(gridL, gridW);
        gridLength = gridL;
        gridWidth = gridW;
    }

    public Level makeLevel(int levelNumber){
        Level level = new Level(levelNumber, gridFactory.makeUniformGrid());

        populateGridWithPlayers(level);
        populateGridWithMonsters(level);

        return level;
    }

    /**
     * places the (maximum) 4 players in the corners of the grid
     */
    private void populateGridWithPlayers(Level level){
        ArrayList<Position> corners = new ArrayList<>(
            List.of(new Position(1,1), new Position(1,gridWidth), new Position(gridLength, 1), new Position(gridLength, gridWidth))
        );

        level.players = new ArrayList<>();
        int i=0;
        for (String name : playerNames){
            level.players.add(new Player(name,corners.get(i), Direction.EAST));
            level.grid.addCharacter(level.players.get(i));
            i++;
        }

        // for debugging
//        grid.printGrid();
    }

    /**
     * places a level-dependent amount of monsters randomly on the grid
     */
    private void populateGridWithMonsters(Level level){
        Random randomGenerator = new Random();
        boolean placedOne;
        boolean tooClose;

        level.monsters = new ArrayList<>();

        for(int monsterCount=0; monsterCount < level.levelNumber*2; monsterCount++){ // 2*N monsters on level N
            placedOne = false;

            while(!placedOne){
                Position pick = new Position(1+randomGenerator.nextInt(gridLength),1+randomGenerator.nextInt(gridWidth)); // random field on the grid
                tooClose = false;

                if(level.grid.getElement(pick).getType() == ElementType.EMPTY){ // if this field is empty
                    for(Player player : level.players) if (Grid.manhattanDistance(player.getPosition(),pick) < 4){ // and it isn't too close to any of the players
                        tooClose = true;
                        break;
                    }
                    if(!tooClose){
                        level.monsters.add(new Monster(pick,Direction.NORTH)); // then add monster
                        level.grid.addCharacter(level.monsters.get(monsterCount));
                        placedOne = true;
                    }
                }
            }

        }

        // for debugging
//        grid.printGrid();
    }

}
