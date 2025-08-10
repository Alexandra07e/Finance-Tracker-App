module com.example.financetracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires java.desktop;
    requires javafx.media;

    opens com.example.financetracker to javafx.fxml;
    exports com.example.financetracker;
    exports com.example.financetracker.controller;
    opens com.example.financetracker.controller to javafx.fxml;
    opens com.example.financetracker.model to javafx.base;
    exports com.example.financetracker.database;
    opens com.example.financetracker.database to javafx.fxml;

}