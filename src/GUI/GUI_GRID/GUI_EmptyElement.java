package GUI.GUI_GRID;

import gameplay.grid.ElementType;
import gameplay.grid.Position;

public class GUI_EmptyElement extends GUI_GridElement{

    public GUI_EmptyElement(Position pos) {
        super(pos);
    }

    @Override
    public ElementType getType() {
        return ElementType.EMPTY;
    }
}
