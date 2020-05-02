package gameplay.grid;

public class EmptyElement implements GridElement {
    @Override
    public ElementType getType() {
        return ElementType.EMPTY;
    }
}
