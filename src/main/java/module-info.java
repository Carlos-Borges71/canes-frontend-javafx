module com.canes {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.canes to javafx.fxml;
    exports com.canes;
}
