package gameplay.grid;

public class Wall implements GridElement{
    protected Position position;
    protected boolean isDestructible;

    public Wall(Position pos, boolean destructible) {
        position=pos;
        isDestructible=destructible;
    }

    protected void explode(){
        if(isDestructible){
            // TODO
        }
    }

    @Override
    public ElementType getType() {
        return ElementType.WALL;
    }
}
