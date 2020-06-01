package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import GUI.GUI_GRID.GUI_Grid;
import GUI.GUI_GRID.GUI_GridElement;
import gameplay.LoggerMan;
import gameplay.grid.*;
import gameplay.logic.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class Controller implements Initializable {

    @FXML
    private AnchorPane mainmenu_pane;

    @FXML
    private Button button_startonlinegame;

    @FXML
    private Label label_waitingconnection;

    @FXML
    private Label label_IPAdress;

    @FXML
    private TextField serverNicknameIn;

    @FXML
    private Label serverNicknameOut;

    @FXML
    private GridPane gridpaneid;

    @FXML
    private void set_grid_resolution(int width, int height){
        gridpaneid.setGridLinesVisible(true);
        for (int i = 0; i < width; i++){
            ColumnConstraints colConst = new ColumnConstraints();
            //colConst.setPercentWidth(100.0 / width);
            gridpaneid.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < height; i++){
            RowConstraints rowConst = new RowConstraints();
            //rowConst.setPercentHeight(100.0 / height);
            gridpaneid.getRowConstraints().add(rowConst);
        }
    }

    @FXML
    private void connected_action(ActionEvent event){
        label_waitingconnection.setText("Connected");
        button_startonlinegame.setVisible(true);
    }

    @FXML
    private void connecttogame_action(ActionEvent event) throws IOException{
        System.out.println("Connecting to the online game...");
        Parent newOnlineGameParent = FXMLLoader.load(getClass().getResource("onlineGame.fxml"));
        Scene newOnlineGameScene = new Scene(newOnlineGameParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newOnlineGameScene);
        window.show();
    }

    @FXML
    private void startoffline_action(ActionEvent event) throws IOException{
        System.out.println("Starting the offline game...");

        LoggerMan.initialize();
        Game game = new Game();
        game.initialize(new ArrayList<String>(List.of("Eric","Bri","Adam")));
        ArrayList<ArrayList<GridElement>> grid = game.initializeNextLevel();

        int width = game.getGridWidth() + 2;
        int height = game.getGridLength() + 2;

        GridPane gridPane = new GridPane();
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
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(gridPane, width * 50, height * 50));
        window.show();

        GUI_Grid gui_grid = new GUI_Grid(height, width, grid);
        //gui_grid.changePosition(new Position(1,1),new Position(1,2));
        gui_grid.swapPosition(new Position(5,1),new Position(4,2));
        gui_grid.addbomb(new Position(1,5),6);
        gui_grid.addbomb(new Position(1,2),55);
        gui_grid.removebomb(55);
        gui_grid.printGUI_Grid(gridPane);
        //gui_grid.printGUI_Grid();

    }

    @FXML
    private void startonline_action(ActionEvent event) throws IOException{
        System.out.println("Starting the online game...");
        Parent newOnlineGameParent = FXMLLoader.load(getClass().getResource("onlineGame.fxml"));
        Scene newOnlineGameScene = new Scene(newOnlineGameParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newOnlineGameScene);
        gridpaneid.setGridLinesVisible(true);
        //set_grid_resolution(200, 150);
        serverNicknameIn.setText(String.valueOf(serverNicknameIn.getCharacters()));
        window.show();

    }

    @FXML
    private void newonline_action(ActionEvent event) throws IOException
    {
        System.out.println("Starting new online game...");
        Parent newOnlineGameParent = FXMLLoader.load(getClass().getResource("NewOnlineGame.fxml"));
        Scene newOnlineGameScene = new Scene(newOnlineGameParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newOnlineGameScene);

        window.show();
    }

    @FXML
    private void newoffline_action(ActionEvent event) throws IOException{
        System.out.println("Starting new offline game...");
        Parent newOnlineGameParent = FXMLLoader.load(getClass().getResource("NewOfflineGame.fxml"));
        Scene newOnlineGameScene = new Scene(newOnlineGameParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newOnlineGameScene);
        window.show();
    }

    @FXML
    private void connectonline_action(ActionEvent event) throws IOException{
        System.out.println("Connecting to online game...");
        Parent newOnlineGameParent = FXMLLoader.load(getClass().getResource("ConnectOnlineGame.fxml"));
        Scene newOnlineGameScene = new Scene(newOnlineGameParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newOnlineGameScene);
        window.show();
    }

    @FXML
    private void viewranglist_action(ActionEvent event) throws IOException{
        System.out.println("Loading ranglist...");
        Parent newOnlineGameParent = FXMLLoader.load(getClass().getResource("Ranglist.fxml"));
        Scene newOnlineGameScene = new Scene(newOnlineGameParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newOnlineGameScene);
        window.show();
    }

    @FXML
    private void backtomain_action(ActionEvent event) throws IOException{
        System.out.println("Back to main menu...");
        Parent newOnlineGameParent = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
        Scene newOnlineGameScene = new Scene(newOnlineGameParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newOnlineGameScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
