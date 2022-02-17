package Controllers;

import DataBase.DBController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Class.Program;
import DataBase.DBController;
import javafx.stage.Stage;
import main.annatelisovandahmadmassalhaproject.Main;

import java.io.IOException;

public class ManagePricesController {

    private Program program = Program.getInstance();
    private DBController dataB = DBController.getInstance();

    public void getPricesFromDataBase()
    {
        double doubleArr[] = dataB.getPrices();
        hairPrice.setText(String.valueOf(doubleArr[0]));
        hairDyePrice.setText(String.valueOf(doubleArr[1]));
        hairStylePrice.setText(String.valueOf(doubleArr[2]));
        menHairPrice.setText(String.valueOf(doubleArr[3]));
        womenHairPrice.setText(String.valueOf(doubleArr[4]));
    }
    @FXML
    private TextField hairDyePrice;

    @FXML
    private TextField hairPrice;

    @FXML
    private TextField hairStylePrice;

    @FXML
    private TextField menHairPrice;

    @FXML
    private TextField womenHairPrice;

    @FXML
    private Button editPrices;

    @FXML
    private Label wentWrong;


    @FXML
    private Button back;

    @FXML
    void initialize() {
        try {
            getPricesFromDataBase();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        back.setOnAction(event ->
        {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ManagerWindow.fxml"));
            Stage addStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene addScene = null;

            try {
                addScene = new Scene(fxmlLoader.load(), 350, 400);
            } catch (IOException e) {
                e.printStackTrace();
            }
            addStage.setScene(addScene);
            addStage.show();
        });
        editPrices.setOnAction(event ->
        {
            try
            {
                program.getPrices(Double.parseDouble(hairPrice.getText()) , Double.parseDouble(hairDyePrice.getText()) ,
                        Double.parseDouble(menHairPrice.getText()) ,
                        Double.parseDouble(womenHairPrice.getText()) ,
                        Double.parseDouble(hairStylePrice.getText()));
                //SQL code
                wentWrong.setVisible(false);
            }catch(Exception e)
            {
                System.out.println(e.getMessage());
                wentWrong.setVisible(true);
            }
        });
    }
}
