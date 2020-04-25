package gameplay.grid;

public class Wall {
    protected Position position;
    protected boolean isDestructible;

    public Wall(Position pos){
        position=pos;
    }

    protected void explode(){
        // TODO
    }

}
