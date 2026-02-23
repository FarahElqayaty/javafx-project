module com.example.beadsfinalproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.beadsfinalproject to javafx.fxml;
    exports com.example.beadsfinalproject;
}