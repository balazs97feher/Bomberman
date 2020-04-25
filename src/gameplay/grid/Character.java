package gameplay.grid;

public abstract class Character {
    protected Position position;
    protected Direction direction;
    protected boolean isAlive;

    public Character(Position pos, Direction dir){
        position=pos;
        direction=dir;
    }

    abstract protected void move(Direction dir);
    abstract protected void die();
}
