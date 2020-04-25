package gameplay.grid;

public class Bomb {
    protected Position position;
    protected int range;
    protected int timeLeft;

    public Bomb(Position pos, int bombRange, int time){
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


}
