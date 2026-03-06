module com.example.java_lab3fx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.java_lab3fx to javafx.fxml;
    exports com.example.java_lab3fx;
}