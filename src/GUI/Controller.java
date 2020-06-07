package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;

import gameplay.LoggerMan;
import gameplay.grid.*;
import gameplay.logic.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Controller implements Initializable {

    private Game game;
    private GUI gui;
    private String localPlayer;

    @FXML
    private AnchorPane mainmenu_pane;

    @FXML
    private Button button_startonlinegame;

    @FXML
    private Label label_waitingconnection;

    @FXML
    private GridPane gridpaneid;

    @FXML
    private TextField offline_nicknameid;

    @FXML
    private TextField client_ipadressid;

    @FXML
    private TextField client_nicknameid;

    @FXML
    private Label server_ipadressid;

    @FXML
    private TextField server_nicknameid;

    @FXML
    private void connected_action(ActionEvent event){
        label_waitingconnection.setText("Connected");
        button_startonlinegame.setVisible(true);
    }

    @FXML
    private void connecttogame_action(ActionEvent event) throws IOException{
        String client_nickname = client_nicknameid.getText();
        String client_ipadress = client_ipadressid.getText();
        Parent newOnlineGameParent = FXMLLoader.load(getClass().getResource("onlineGame.fxml"));
        Scene newOnlineGameScene = new Scene(newOnlineGameParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newOnlineGameScene);
        window.show();
    }

    @FXML
    private void startOfflineGame(ActionEvent event) throws IOException{
        localPlayer = offline_nicknameid.getText();
        LoggerMan.log(Level.INFO, "Offline game started.");

        game = new Game();
        game.initialize(new ArrayList<String>(List.of(localPlayer)));
        ArrayList<ArrayList<GridElement>> grid = game.initializeNextLevel();

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        gui = new GUI(game.getGridLength() + 2, game.getGridWidth() + 2, grid, window);
        gui.setScore_label(localPlayer);

        attachKeyboard(gui.window.getScene());

        game.startLevel();
        Renderer guiRenderer = new Renderer(game,gui);
        Thread rendererThread = new Thread(guiRenderer);
        rendererThread.start();
    }

    @FXML
    private void startonline_action(ActionEvent event) throws IOException{
        localPlayer = server_nicknameid.getText();
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        LoggerMan.initialize();
        Game game = new Game();
        game.initialize(new ArrayList<String>(List.of(localPlayer)));
        ArrayList<ArrayList<GridElement>> grid = game.initializeNextLevel();
        GUI gui = new GUI(game.getGridLength() + 2, game.getGridWidth() + 2, grid, window);
        gui.setScore_label(localPlayer);
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
    private void newOfflineGame(ActionEvent event) throws IOException{
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

    private void movePlayer(String player, Direction direction){
        if(game != null){
            game.movePlayer(player,direction);
        }
    }

    private void placeBomb(String player){
        if(game != null){
            game.placeBomb(player);
        }
    }

    private void attachKeyboard(Scene scene){
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch(keyEvent.getCode()){
                    case UP: movePlayer(localPlayer, Direction.NORTH); break;
                    case DOWN: movePlayer(localPlayer, Direction.SOUTH); break;
                    case RIGHT: movePlayer(localPlayer, Direction.EAST); break;
                    case LEFT: movePlayer(localPlayer, Direction.WEST);; break;
                    case SPACE: placeBomb(localPlayer); break;
                }
            }
        });
    }

}
