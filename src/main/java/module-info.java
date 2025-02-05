module com.example.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jfr;
    requires java.sql;


    opens com.example.project to javafx.fxml;
    exports com.example.project;
}