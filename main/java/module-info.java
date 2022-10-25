module com.javafxprojectday19 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.javafxprojectday19 to javafx.fxml;
    exports com.javafxprojectday19;
}