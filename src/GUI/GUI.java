package GUI;

import GUI.GUI_GRID.GUI_Grid;
import gameplay.grid.GridElement;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GUI {
    protected GUI_Grid grid;
    protected GridPane gridPane;
    protected Label score_label;
    protected int width;
    protected  int height;
    protected Stage window;

    public GUI(int h, int w, ArrayList<ArrayList<GridElement>> game_grid, Stage stage){
        width = w;
        height = h;
        gridPane = new GridPane();
        setGridpaneLayout(w, h);
        score_label = new Label();
        grid = new GUI_Grid(h,w,game_grid);
        VBox vbox = new VBox(score_label, gridPane);
        window = stage;
        window.setScene(new Scene(vbox));
        window.show();
        grid = new GUI_Grid(h,w,game_grid);
        grid.printGUI_Grid(gridPane);
    }

    private void setGridpaneLayout(int w, int h){
        gridPane.setGridLinesVisible(true);
        gridPane.setPrefHeight(height * 50);
        gridPane.setPrefWidth(width * 50);

        for (int i = 0; i < width; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / width);
            gridPane.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < height; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / height);
            gridPane.getRowConstraints().add(rowConst);
        }
    }

    public void setScore_label(String str){
        score_label.setText(str);
    }
}
