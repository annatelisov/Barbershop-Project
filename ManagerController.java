package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import Class.Program;
import main.annatelisovandahmadmassalhaproject.Main;

public class ManagerController {
    private Program program = Program.getInstance();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button allAppointmentsButton;

    @FXML
    private Button cancelAppointmentsButton;

    @FXML
    private Button changeWorkTimesButton;

    @FXML
    private Button closeButton;

    @FXML
    private Button profitButtom;

    @FXML
    private Button setPrices;

    //FOR TIME MANAGING------------------------------
    @FXML
    private Button doneTimes;

    @FXML
    private TextField eTimeTxt;

    @FXML
    private Label endTime;

    @FXML
    private Label invalidTime;

    @FXML
    private Rectangle rect;

    @FXML
    private TextField sTimeTxt;

    @FXML
    private Button closeTimesButton;

    @FXML
    private Label startTime;

    @FXML
    private Label profit;

    @FXML
    private Label weeksProfitLbl;


    @FXML
    void initialize() {
        ClientController.managerView = true;
        closeButton.setOnAction(event -> {//when pressing close button
            Stage addStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            addStage.close();
        });

        setPrices.setOnAction(event -> {//get to the manager prices page
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ManagerPrices.fxml"));
            Stage addStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene addScene = null;

            try {
                addScene = new Scene(fxmlLoader.load(), 350, 400);
            } catch (IOException e) {
                e.printStackTrace();
            }
            addStage.setScene(addScene);
            addStage.show();
            System.out.println("PRICES");
        });

        allAppointmentsButton.setOnAction(event ->{

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ClientWindow.fxml"));
            Stage addStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene addScene = null;

            try {
                addScene = new Scene(fxmlLoader.load(), 350, 400);
            } catch (IOException e) {
                e.printStackTrace();
            }
            addStage.setScene(addScene);
            addStage.show();
            System.out.println("PRICES");
        });

        changeWorkTimesButton.setOnAction(event ->{
            eTimeTxt.setText(String.valueOf(program.getCloseTime()));
            sTimeTxt.setText(String.valueOf(program.getOpenTime()));
            rect.setVisible(true);
            doneTimes.setVisible(true);
            eTimeTxt.setVisible(true);
            startTime.setVisible(true);
            endTime.setVisible(true);
            sTimeTxt.setVisible(true);
            closeTimesButton.setVisible(true);
        });
        doneTimes.setOnAction(event ->{
            int startingTime = 0;
            int endingTime = 0;
            startingTime = Integer.parseInt(sTimeTxt.getText());
            endingTime = Integer.parseInt(eTimeTxt.getText());
            if(startingTime > endingTime || startingTime > 23 || startingTime < 0 || endingTime > 23 || endingTime < 0)
                invalidTime.setVisible(true);
            else
            {
                invalidTime.setVisible(false);
                program.setStartEndTimes(startingTime , endingTime);
                rect.setVisible(false);
                doneTimes.setVisible(false);
                eTimeTxt.setVisible(false);
                startTime.setVisible(false);
                endTime.setVisible(false);
                sTimeTxt.setVisible(false);
                closeTimesButton.setVisible(false);
            }
        });
        closeTimesButton.setOnAction(event ->{
            rect.setVisible(false);
            doneTimes.setVisible(false);
            eTimeTxt.setVisible(false);
            startTime.setVisible(false);
            endTime.setVisible(false);
            sTimeTxt.setVisible(false);
            closeTimesButton.setVisible(false);
            profit.setVisible(false);
            weeksProfitLbl.setVisible(false);
        });

        profitButtom.setOnAction(event->{
            program.getAllAppointmentFromDB();
            program.getPricesFromDB();
            closeTimesButton.setVisible(true);
            rect.setVisible(true);
            profit.setText(String.valueOf(program.calculateProfit()));
            profit.setVisible(true);
            weeksProfitLbl.setVisible(true);
        });
    }
}

