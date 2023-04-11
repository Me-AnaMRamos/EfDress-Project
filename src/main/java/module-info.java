module com.example.efdressfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.efdressfx to javafx.fxml;
    exports com.example.efdressfx;
}