package gameplay.grid;

public class Wall extends GridElement{
    protected Position position;
    protected boolean isDestructible;

    public Wall(Position pos, boolean destructible) {
        super(pos);
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

    @Override
    public Position getPosition() {
        return position;
    }
}
