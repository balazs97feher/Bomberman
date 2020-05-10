package gameplay.grid;

public class Bomb extends GridElement{
    private int id;
    private static int nextId = 0;

    protected int range;
    protected int timeLeft;

    public Bomb(Position pos, int bombRange, int time){
        super(pos);
        position = pos;
        range = bombRange;
        timeLeft = time;
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
