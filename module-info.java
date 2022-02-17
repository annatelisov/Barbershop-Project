module main.annatelisovandahmadmassalhaproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens main.annatelisovandahmadmassalhaproject to javafx.fxml;
    exports main.annatelisovandahmadmassalhaproject;


    opens Controllers to javafx.fxml;
    exports Controllers;
}