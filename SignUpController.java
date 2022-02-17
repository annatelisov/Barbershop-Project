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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.annatelisovandahmadmassalhaproject.Main;

public class SignUpController {
    DBController dataB = DBController.getInstance();

    @FXML
    private Label errorReg;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton femaleButton;

    @FXML
    private TextField firstNameSignUpText;

    @FXML
    private Button goBackButton2;

    @FXML
    private TextField lastNameSignUpText;

    @FXML
    private RadioButton maleButton;

    @FXML
    private TextField passwordSignUpText;

    @FXML
    private TextField phoneSignUpText;

    @FXML
    private Button registerButton;

    @FXML
    private TextField usernameSignUpText;

    @FXML
    void initialize() {
        errorReg.setVisible(false);
        registerButton.setOnAction(event -> {
            int gender = 1;
            if(femaleButton.isSelected()) gender = 2;
            boolean success = false;
            success = dataB.registerUser(firstNameSignUpText.getText() , lastNameSignUpText.getText() , gender , phoneSignUpText.getText() ,
                    usernameSignUpText.getText() , passwordSignUpText.getText()); //using a bool we can know if to show an error message
            if(success) {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("WelcomeWindow.fxml"));
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
            }else
            {
                errorReg.setVisible(true);
            }
        });

        goBackButton2.setOnAction(event -> {
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
