package gameplay.grid;

public abstract class GridElement {
    protected Position position;

    public GridElement(Position pos){
        position = pos;
    }

    public abstract ElementType getType();

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position newPosition){
        position = newPosition;
    }
}
