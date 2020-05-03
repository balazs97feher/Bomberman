package gameplay.grid;

public abstract class Character extends GridElement{
    protected Direction direction; // in which direction is the character moving
    protected boolean isAlive;

    public Character(Position pos, Direction dir){
        super(pos);
        direction=dir;
    }

    abstract protected void move(Direction dir);
    abstract protected void die();

    @Override
    public Position getPosition(){
        return position;
    }
}
