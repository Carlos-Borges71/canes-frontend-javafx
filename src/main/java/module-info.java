module com.canes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;

    opens com.canes to javafx.fxml;
    exports com.canes;
}
