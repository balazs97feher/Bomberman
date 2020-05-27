package gameplay.grid;

public class Bomb extends GridElement{
    private int id;
    private static int nextId = 0;

    protected int range;
    protected int timeLeft;

    public Bomb(Position pos){
        super(pos);
        position = pos;
        range = 2;
        timeLeft = 2;
        id = nextId;
        nextId++;
    }

    protected void explode(){
        // TODO
    }

    protected void tick(){
        // TODO
    }


    @Override
    public ElementType getType() {
        return ElementType.BOMB;
    }

}
