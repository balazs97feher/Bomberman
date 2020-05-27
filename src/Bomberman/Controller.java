package Bomberman;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.layout.AnchorPane;
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
        Parent newOnlineGameParent = FXMLLoader.load(getClass().getResource("offlineGame.fxml"));
        Scene newOnlineGameScene = new Scene(newOnlineGameParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newOnlineGameScene);
        window.show();
    }

    @FXML
    private void startonline_action(ActionEvent event) throws IOException{
        System.out.println("Starting the online game...");
        Parent newOnlineGameParent = FXMLLoader.load(getClass().getResource("onlineGame.fxml"));
        Scene newOnlineGameScene = new Scene(newOnlineGameParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(newOnlineGameScene);
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
