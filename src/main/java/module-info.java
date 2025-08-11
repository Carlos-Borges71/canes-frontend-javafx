module com.canes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.canes to javafx.fxml;
    exports com.canes;
}
