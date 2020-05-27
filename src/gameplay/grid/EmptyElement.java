package gameplay.grid;

public class EmptyElement extends GridElement {

    public EmptyElement(Position pos) {
        super(pos);
    }

    @Override
    public ElementType getType() {
        return ElementType.EMPTY;
    }

}
