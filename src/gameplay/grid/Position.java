package gameplay.grid;

/**
 *  class to store the position of an item on the game grid
 */
public class Position {
    protected int posY; // grid row coordinate
    protected int posX; // grid column coordinate

    public Position(int y, int x){
        posY = y;
        posX = x;
    }
}
