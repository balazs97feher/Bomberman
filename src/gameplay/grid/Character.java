package gameplay.grid;

public abstract class Character extends GridElement{
    protected Direction direction; // in which direction is the character moving
    protected boolean isAlive;

    public Character(Position pos, Direction dir){
        super(pos);
        direction = dir;
        isAlive = true;
    }

    abstract public void move(Direction dir);
    abstract public void die();

    public Direction getDirection(){
        return direction;
    }
}
