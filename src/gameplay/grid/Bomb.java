package gameplay.grid;

public class Bomb extends GridElement{
    private int id;
    private Player owner;
    private static int nextId = 0;

    protected int range;
    protected int detonationTime;

    public Bomb(Position pos, Player player){
        super(pos);
        owner = player;
        range = 2;
        detonationTime = 2000; // milliseconds
        id = nextId;
        nextId++;
    }

    public Player getOwner() {
        return owner;
    }

    public int getId(){
        return id;
    }

    public int getDetonationTime() {
        return detonationTime;
    }

    @Override
    public ElementType getType() {
        return ElementType.BOMB;
    }
}
