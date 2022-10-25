module com.demoproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.demoproject to javafx.fxml;
    exports com.demoproject;
}