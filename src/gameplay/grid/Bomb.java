package gameplay.grid;

public class Bomb extends GridElement{
    protected int range;
    protected int timeLeft;

    public Bomb(Position pos, int bombRange, int time){
        super(pos);
        position=pos;
        range=bombRange;
        timeLeft=time;
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

    @Override
    public Position getPosition() {
        return position;
    }
}
