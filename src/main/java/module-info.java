module com.example.sudokugui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sudokugui to javafx.fxml;
    exports com.example.sudokugui;
}