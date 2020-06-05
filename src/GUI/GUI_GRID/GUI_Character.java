package GUI.GUI_GRID;

import gameplay.grid.Direction;
import gameplay.grid.Position;

public abstract class GUI_Character extends GUI_GridElement{
    protected Direction direction;
    protected int id;

    public GUI_Character(Position pos, Direction dir, int Id){
        super(pos);
        direction = dir;
        id = Id;
    }

    public Direction getDirection(){
        return direction;
    }

    public void setDirection(Direction dir){
        direction = dir;
    }
}
