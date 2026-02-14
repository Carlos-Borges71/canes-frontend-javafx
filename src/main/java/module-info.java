module com.canes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires org.json;
    requires com.google.gson;

    opens com.canes.controller to javafx.fxml;

    exports com.canes;
    exports com.canes.util;
    exports com.canes.model;
    exports com.canes.model.dpo;

    opens com.canes.model to com.fasterxml.jackson.databind;
    opens com.canes.model.pk to com.fasterxml.jackson.databind;

}
