package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DataBase.DBController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.annatelisovandahmadmassalhaproject.Main;
import javafx.scene.control.TextField;

public class WelcomeController {

    private DBController dataB = DBController.getInstance();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ManagerLoginButton;

    @FXML
    private Button SignInButton;

    @FXML
    private Button SignUpButton;

    @FXML
    private TextField managerPasswordText;

    @FXML
    private Button goToManagerButton;

    @FXML
    private Label wrongPass;

    @FXML
     void initialize() {
        SignInButton.setOnAction(event -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SignInWindow.fxml"));
            Stage addStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene addScene = null;
            try {
                addScene = new Scene(fxmlLoader.load(), 350, 400);
            } catch (IOException e) {
                e.printStackTrace();
            }
            addStage.setTitle("Sign in");
                    addStage.setScene(addScene);
                    addStage.show();
            });

        SignUpButton.setOnAction(event -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SignUpWindow.fxml"));
            Stage addStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene addScene = null;
            try {
                addScene = new Scene(fxmlLoader.load(), 350, 400);
            } catch (IOException e) {
                e.printStackTrace();
            }
            addStage.setTitle("Sign up");
            addStage.setScene(addScene);
            addStage.show();
        });

        ManagerLoginButton.setOnAction(event -> {
            managerPasswordText.setVisible(true);
            goToManagerButton.setVisible(true);
        });

        goToManagerButton.setOnAction(event -> {
            if(dataB.checkManager(managerPasswordText.getText()) == false)
            {
                wrongPass.setVisible(true);
                return;
            }
            wrongPass.setVisible(false);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ManagerWindow.fxml"));
            Stage addStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene addScene = null;
            try {
                addScene = new Scene(fxmlLoader.load(), 350, 400);
            } catch (IOException e) {
                e.printStackTrace();
            }
            addStage.setTitle("Manager list");
            addStage.setScene(addScene);
            addStage.show();
        });

    }

}
