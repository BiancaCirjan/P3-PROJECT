module com.example.blushbloom_b {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.blushbloom_b to javafx.fxml;
    exports com.example.blushbloom_b;
}