module com.canes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;

    opens com.canes.controller to javafx.fxml;
    exports com.canes;
    exports com.canes.model;
    exports com.canes.model.dpo;

}
