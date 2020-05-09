package gameplay.grid;

import java.util.ArrayList;

public class Grid {
    protected int length;
    protected int width;
    public ArrayList<ArrayList<GridElement>> elements;

    public Grid(int l, int w, ArrayList<ArrayList<GridElement>> gridElements){
        length = l;
        width = w;
        elements = gridElements;
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

    public void setElement(GridElement element, Position position) {
        elements.get(position.posY).set(position.posX,element);
        element.setPosition(position);
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
