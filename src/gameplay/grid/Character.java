package gameplay.grid;

public abstract class Character extends GridElement{
    protected Direction direction; // in which direction is the character moving
    protected int id;

    public Character(Position pos, Direction dir){
        super(pos);
        direction = dir;
    }

    public Direction getDirection(){
        return direction;
    }
    public void setDirection(Direction dir){
        direction = dir;
    }
}
