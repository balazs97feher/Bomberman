package gameplay.grid;

public class Player extends Character{
    protected String name;

    public Player(String charName, Position pos, Direction dir) {
        super(pos, dir);
        name=charName;
    }

    @Override
    protected void move(Direction dir) {
        // TODO
    }

    @Override
    protected void die() {
        isAlive=false;
    }


    @Override
    public ElementType getType() {
        return ElementType.PLAYER;
    }
}
