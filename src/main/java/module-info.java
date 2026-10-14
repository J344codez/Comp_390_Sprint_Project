module com.example.sprint_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sprint_1 to javafx.fxml;
    exports com.example.sprint_1;
}