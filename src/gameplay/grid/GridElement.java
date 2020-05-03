package gameplay.grid;

public abstract class GridElement {
    protected Position position;

    public GridElement(Position pos){
        position = pos;
    }

    public abstract ElementType getType();
    public abstract Position getPosition();
}
