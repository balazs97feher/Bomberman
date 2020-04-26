import gameplay.logic.gridfactory.GridFactory;

public class Main {
    public static void main(String[] args) {


        GridFactory gridFactory=new GridFactory(7,5);
        gridFactory.makeUniformGrid();
    }
}
