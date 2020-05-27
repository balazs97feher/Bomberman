package gameplay.grid;

public class Player extends Character{
    protected String name;
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

    public String getName() { return name; }

    @Override
    public ElementType getType() {
        return ElementType.PLAYER;
    }
}
