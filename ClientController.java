package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import main.annatelisovandahmadmassalhaproject.Main;
import Class.Program;
public class ClientController {

    Program program = Program.getInstance();

    public static boolean managerView = false;
    @FXML
    private Label titleOfWindow;

    @FXML
    private ResourceBundle resources;

    @FXML
    private ScrollPane appsPane;
////////////////////
    @FXML
    private Button deleteAppt;

    @FXML
    private Label deleteText;

    @FXML
    private Button exitListButton;
////////////////////
    @FXML
    private ListView<String> listView;

    @FXML
    private URL location;

    @FXML
    private Button appointmentButton;

    @FXML
    private Button closeButton;

    @FXML
    private Button lookAtAppointmentButton;

    @FXML
    private Button pricesButton;

    @FXML
    void initialize() {
        program.getAllAppointmentFromDB(); //to get all appointments , this happens one
        program.getPricesFromDB();         //to get all prices , this happens one

        pricesButton.setOnAction(event -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PricesWindow.fxml"));
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
        appointmentButton.setOnAction(event -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AppointmentWindow.fxml"));
            Stage addStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene addScene = null;
            try {
                addScene = new Scene(fxmlLoader.load(), 350, 400);
            } catch (IOException e) {
                e.printStackTrace();
            }
            addStage.setTitle("Your Appointment");
            addStage.setScene(addScene);
            addStage.show();
        });
        lookAtAppointmentButton.setOnAction(event ->{
            getApptsToList();
            listView.setVisible(true);
            deleteAppt.setVisible(true);
            deleteText.setVisible(true);
            exitListButton.setVisible(true);
        });
        exitListButton.setOnAction(event ->{
            listView.setVisible(false);
            deleteAppt.setVisible(false);
            deleteText.setVisible(false);
            exitListButton.setVisible(false);
            deleteText.setText("To remove an appointment, select it then press Delete");
            if(managerView) // goes back to manager window if needed, in case manager turns this window on
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
            }

        });
        deleteAppt.setOnAction(event->{
            boolean isSelected = false;
            for(int i = 0 ; i < listView.getItems().size() ; i++) {
                isSelected = listView.getSelectionModel().isSelected(i);
                if(isSelected) break;
            }
            if(isSelected == false) deleteText.setText("You have to select an appointment");
            else
            {
                program.removeAppointmentAt(listView.getSelectionModel().getSelectedIndex());
                deleteText.setText("Appointment deleted!");
                getApptsToList();
            }
        });
        closeButton.setOnAction(event -> {
            Stage addStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            addStage.close();
        });
        if(managerView)
        {
            titleOfWindow.setText("   Remove Appointments");
            lookAtAppointmentButton.fire();
        }
    }
    private void getApptsToList(){//To get appointments prepared in list:
        listView.getItems().clear();
        Vector<String> appts = program.getAppointmentsForView();
        for(int i = 0 ; i < appts.size() ; i++)
        {
            listView.getItems().add(appts.elementAt(i));
        }
    }
}
