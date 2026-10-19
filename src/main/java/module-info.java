module com.example.sprint_1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.desktop;

    opens com.example.sprint_1 to javafx.fxml;
    exports com.example.sprint_1;
}