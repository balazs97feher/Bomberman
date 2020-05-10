package gameplay.grid;

public class Player extends Character{
    protected String name;
    private int id;
    private static int nextId = 0;

    public Player(String charName, Position pos, Direction dir) {
        super(pos, dir);
        name = charName;
        id = nextId;
        nextId++;
    }

    public int getId() {
        return id;
    }

    @Override
    public void die() {
        isAlive = false;
    }

    @Override
    public ElementType getType() {
        return ElementType.PLAYER;
    }
}
