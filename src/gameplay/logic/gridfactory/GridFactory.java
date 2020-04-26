package gameplay.logic.gridfactory;

import gameplay.grid.*;

import java.util.ArrayList;

public class GridFactory {
    private int length; // vertical size of grid
    private int width; // horizontal size of grid

    // we need odd dimensions to create a uniform grid
    public GridFactory(int l, int w){
        if(l%2==1) length=l+2;
        else length=l+3;
        if(w%2==1) width=w+2;
        else width=w+3;
    }

    public Grid makeUniformGrid(){
        ArrayList<ArrayList<GridElement>> elements = new ArrayList<ArrayList<GridElement>>(length);
        for(int i=0; i<length; i++) elements.add(new ArrayList<GridElement>(width));
        for(int i=0;i<length;i++) for(int j=0;j<width;j++) elements.get(i).add(new EmptyElement());

        // top and bottom borders
        for(int i=0;i<width;i++){
            elements.get(0).set(i,new Wall(new Position(0,i),false));
            elements.get(length-1).set(i,new Wall(new Position(length-1,i),false));
        }

        // left and right borders
        for(int j=1;j<length-1;j++){
            elements.get(j).set(0,new Wall(new Position(j,0),false));
            elements.get(j).set(width-1,new Wall(new Position(j,width-1),false));
        }

        // walls in the middle in a uniform grid
        for(int i=2;i<=length-3;i+=2) for(int j=2;j<=width-3;j+=2){
                elements.get(i).set(j,new Wall(new Position(i,j),false));
            }

        // for debugging
        for (ArrayList<GridElement> row : elements) {
            for(GridElement element : row){
                System.out.println(element.getClass().getName() + " ");
            }
            System.out.println("\n");
        }

        return new Grid(length,width,elements);
    }

}
