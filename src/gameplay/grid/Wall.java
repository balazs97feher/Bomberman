package gameplay.grid;

public class Wall extends GridElement{
    private int id;
    private static int nextId = 0;

    protected Position position;
    protected boolean isDestructible;

    public Wall(Position pos, boolean destructible) {
        super(pos);
        isDestructible = destructible;
        id = nextId;
        nextId++;
    }

    protected void explode(){
        if(isDestructible){
            // TODO
        }
    }

    public int getId(){return id;}

    public boolean getDestructible(){return isDestructible;}

    @Override
    public ElementType getType() {
        return ElementType.WALL;
    }
}
