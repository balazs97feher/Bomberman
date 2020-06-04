package gameplay.grid;

public class Bomb extends GridElement{
    private int id;
    private static int nextId = 0;

    protected int range;
    protected int detonationTime;

    public Bomb(Position pos){
        super(pos);
        position = pos;
        range = 2;
        detonationTime = 2000; // milliseconds
        id = nextId;
        nextId++;
    }

    public int getDetonationTime(){
        return detonationTime;
    }


    @Override
    public ElementType getType() {
        return ElementType.BOMB;
    }

}
