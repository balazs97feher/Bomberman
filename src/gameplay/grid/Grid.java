package gameplay.grid;

import java.util.ArrayList;

public class Grid {
    protected int length;
    protected int width;
    protected ArrayList<ArrayList<GridElement>> elements;

    public Grid(int l, int w, ArrayList<ArrayList<GridElement>> gridElements){
        length = l;
        width = w;
        elements = gridElements;
    }

}
