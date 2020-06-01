package GUI.GUI_GRID;

import gameplay.grid.ElementType;
import gameplay.grid.Position;

public class GUI_Bomb extends GUI_GridElement {
    private int id;

    public GUI_Bomb(Position pos, int Id) {
        super(pos);
        id = Id;
    }

    @Override
    public ElementType getType() {
        return ElementType.BOMB;
    }
}
