module com.canes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;

    opens com.canes.controller to javafx.fxml;
    exports com.canes;
    exports com.canes.model;
    exports com.canes.model.dpo;
    opens com.canes.model to com.fasterxml.jackson.databind;

}
