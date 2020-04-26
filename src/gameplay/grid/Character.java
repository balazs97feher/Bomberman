package gameplay.grid;

public abstract class Character {
    protected Position position; // character's position on the grid
    protected Direction direction; // in which direction is the character moving
    protected boolean isAlive;

    public Character(Position pos, Direction dir){
        position=pos;
        direction=dir;
    }

    abstract protected void move(Direction dir);
    abstract protected void die();
}
