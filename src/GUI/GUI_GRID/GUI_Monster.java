package GUI.GUI_GRID;

import gameplay.grid.Direction;
import gameplay.grid.ElementType;
import gameplay.grid.Position;

public class GUI_Monster extends GUI_Character{

    public GUI_Monster(Position pos, Direction dir, int id) {
        super(pos, dir, id);
    }

    public int getId(){
        return id;
    }

    @Override
    public ElementType getType() {
        return ElementType.MONSTER;
    }
}
