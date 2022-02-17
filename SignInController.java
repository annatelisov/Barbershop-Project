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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.annatelisovandahmadmassalhaproject.Main;

import Class.Program;
public class SignInController {
    private DBController dataB = DBController.getInstance();
    private Program program = Program.getInstance();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button SignInToSystemButton;

    @FXML
    private Button goBackButton;

    @FXML
    private TextField usernameInSignInText;

    @FXML
    private PasswordField pwordSignIn;

    @FXML
    private Label wrongPassLabel;

    @FXML
    void initialize() {
        SignInToSystemButton.setOnAction(event -> {
            System.out.println("USED SIGN IN");
            if(dataB.checkLoginData(usernameInSignInText.getText(),pwordSignIn.getText()) == false)
            {
                //throw exepction
                wrongPassLabel.setVisible(true);
                return;
            }else
            {
                wrongPassLabel.setVisible(false);
            }
            program.setCurrentUser(usernameInSignInText.getText()); //For the program to set current user.
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ClientWindow.fxml"));
            Stage addStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene addScene = null;

            try {
                addScene = new Scene(fxmlLoader.load(), 350, 400);
            } catch (IOException e) {
                e.printStackTrace();
            }
            addStage.setTitle("Your details");
            addStage.setScene(addScene);
            addStage.show();
        });

        goBackButton.setOnAction(event -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("WelcomeWindow.fxml"));
            Stage addStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene addScene = null;
            try {
                addScene = new Scene(fxmlLoader.load(), 350, 400);
            } catch (IOException e) {
                e.printStackTrace();
            }
            addStage.setTitle("Welcome!");
            addStage.setScene(addScene);
            addStage.show();
        });
    }

}
