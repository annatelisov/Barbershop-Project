package Controllers;

import DataBase.DBController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.annatelisovandahmadmassalhaproject.Main;

import java.io.IOException;

public class PricesForClient {

    DBController dataB = DBController.getInstance();
    @FXML
    private Label priceLabel;

    @FXML
    private Button closeButton;

    private void getPricesFromDB(Label label)
    {
        double[] arr = dataB.getPrices();
        label.setText("Haircut Price: " + arr[0] + "\n" +
                      "Hair Dye Price: " + arr[1] + "\n"+
                      "For Men Fee: " + arr[2] + "\n"+
                       "For Women Free: " + arr[3] + "\n" +
                       "Hair Style Price: " + arr[4] + "\n");
    }
    @FXML
    void initialize() {
        getPricesFromDB(priceLabel);
        closeButton.setOnAction(event -> {

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ClientWindow.fxml"));
            Stage addStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene addScene = null;
            try {
                addScene = new Scene(fxmlLoader.load(), 350, 400);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //addStage.setTitle("Your Appointment");
            addStage.setScene(addScene);
            addStage.show();
    });
}
}
