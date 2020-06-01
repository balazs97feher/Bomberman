package GUI.GUI_GRID;

import gameplay.grid.ElementType;
import gameplay.grid.Position;

public class GUI_WALL extends GUI_GridElement {

    private int id;

    protected boolean isDestructible;

    public GUI_WALL(Position pos, boolean destructible, int Id) {
        super(pos);
        position = pos;
        isDestructible = destructible;
        id = Id;
    }

    public boolean getDestructible(){return isDestructible;}

    @Override
    public ElementType getType() {
        return ElementType.WALL;
    }
}
