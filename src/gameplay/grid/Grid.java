package gameplay.grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Grid {
    protected int length;
    protected int width;
    protected ArrayList<ArrayList<GridElement>> elements;

    // an array to easily iterate through all the directions
    public static final ArrayList<Direction> directions = new ArrayList<>(Arrays.asList(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST));
    // a map to quickly find a direction's opposite
    public static final Map<Direction,Direction> oppositeDirections =
        Map.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH, Direction.WEST, Direction.EAST);

    public Grid(int l, int w, ArrayList<ArrayList<GridElement>> gridElements){
        length = l;
        width = w;
        elements = gridElements;
    }

    public ArrayList<ArrayList<GridElement>> getElements(){
        return elements;
    }

    public void addCharacter(Character character){
        elements.get(character.position.posY).set(character.position.posX,character);
    }

    /**
     * calculates the manhattan distance of two grid elements
     * @param first
     * @param second
     * @return
     */
    static public int manhattanDistance(GridElement first, GridElement second){
        return Math.abs(first.getPosition().posX-second.getPosition().posX)+Math.abs(first.getPosition().posY-second.getPosition().posY);
    }

    /**
     * calculates the manhattan distance of two position instances
     * @param first
     * @param second
     * @return
     */
    static public int manhattanDistance(Position first, Position second){
        return Math.abs(first.posX-second.posX)+Math.abs(first.posY-second.posY);
    }

    /**
     * returns the grid element at the specified position
     * @param position
     * @return
     */
    public GridElement getElement(Position position){
        return elements.get(position.posY).get(position.posX);
    }

    /**
     * sets the specified element at the specified position on the grid
     * @param position
     * @param element
     */
    public void setElement(Position position, GridElement element) {
        elements.get(position.posY).set(position.posX,element);
        element.setPosition(position);
    }

    /**
     * swaps the two specified elements on the grid
     * @param first
     * @param second
     */
    public void swapElements(GridElement first, GridElement second){
        Position firstPosition = first.getPosition();
        setElement(second.getPosition(), first);
        setElement(firstPosition, second);
    }

    /**
     * returns the specified neighbor grid element of the input gird element
     * @param position
     * @param direction
     * @return
     */
    public GridElement getNeighbor(Position position,Direction direction) {
        try{
            switch (direction){
                case NORTH:
                    return elements.get(position.posY-1).get(position.posX);
                case EAST:
                    return elements.get(position.posY).get(position.posX+1);
                case SOUTH:
                    return elements.get(position.posY+1).get(position.posX);
                case WEST:
                    return elements.get(position.posY).get(position.posX-1);
                default:
                    return null;
            }
        }
        catch (Exception e){
            return null;
        }
    }

    public void printGrid(){
        for (ArrayList<GridElement> row : elements) {
            for(GridElement element : row){
                switch (element.getType()){
                    case BOMB:
                        System.out.print("B");
                        break;
                    case WALL:
                        System.out.print("W");
                        break;
                    case PLAYER:
                        System.out.print("P");
                        break;
                    case MONSTER:
                        System.out.print("M");
                        break;
                    case EMPTY:
                        System.out.print("E");
                        break;
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }



}
