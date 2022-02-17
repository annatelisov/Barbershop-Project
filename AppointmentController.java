package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import main.annatelisovandahmadmassalhaproject.Main;
import Class.Program;
public class AppointmentController {
        Program program = Program.getInstance();
        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private Button backToMainButton;

        @FXML
        private RadioButton hairStyleChoiceButton;

        @FXML
        private RadioButton hairColorChoseButton;

        @FXML
        private RadioButton haircutChoseButton;

        @FXML
        private Button saveAppointmentButton;

        @FXML
        private ListView<String> timeChoice;

        @FXML
        private ListView<String> dayChoice;

        @FXML
        void initialize() {
            dayChoice.getItems().addAll("Sunday" , "Monday" , "Tuesday" , "Wednesday" , "Thursday" , "Friday" , "Saturday");
            dayChoice.setOnMouseClicked(event ->{
                System.out.println(dayChoice.getSelectionModel().getSelectedIndex());
                timeChoice.getItems().clear();
                int[] times = program.getAvailableTimeForDay(dayChoice.getSelectionModel().getSelectedIndex());
                //System.out.println(times.toString());
                for(int i = 0 ; i < times.length ; i++)
                {
                    String timeToString = String.valueOf(times[i]) + ":00";
                    timeChoice.getItems().add(timeToString);
                }
            });
            backToMainButton.setOnAction(event -> {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ClientWindow.fxml"));
                Stage addStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene addScene = null;
                try {
                    addScene = new Scene(fxmlLoader.load(), 350, 400);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                addStage.setTitle("Your details!");
                addStage.setScene(addScene);
                addStage.show();
            });
            //day and time choice are the indexes of the choice in the FX list but we added one to it.
            saveAppointmentButton.setOnAction(event -> {
                StringBuilder time = new StringBuilder(timeChoice.getSelectionModel().getSelectedItem());
                time.replace(time.indexOf(":") , time.length() , "");
                //System.out.println(time.toString());
                program.addAppointment(haircutChoseButton.isSelected() ,hairColorChoseButton.isSelected() , hairStyleChoiceButton.isSelected() ,
                                        dayChoice.getSelectionModel().getSelectedIndex() + 1 , Integer.parseInt(time.toString()) , true);

                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ClientWindow.fxml"));
                Stage addStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene addScene = null;
                try {
                    addScene = new Scene(fxmlLoader.load(), 350, 400);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                addStage.setTitle("Your details!");
                addStage.setScene(addScene);
                addStage.show();
            });
        }

    }


