package GUI.GUI_GRID;

import gameplay.grid.ElementType;
import gameplay.grid.Position;
import javafx.geometry.Pos;

public abstract class GUI_GridElement {
    protected Position position;

    public GUI_GridElement(Position pos){position = pos;}

    public abstract ElementType getType();

    public Position getPosition(){ return position;}

    public void setPosition(Position newPosition){position = newPosition;}
}
