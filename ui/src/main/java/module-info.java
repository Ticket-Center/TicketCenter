module ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.data.jpa;
    requires persistence;
    requires lombok;
    requires spring.boot;
    requires spring.beans;
    requires spring.core;
    //requires oop.ticketcenter.persistence;
//    requires javafx.web;
//
//    requires org.controlsfx.controls;
//    requires com.dlsc.formsfx;
//    requires net.synedra.validatorfx;
//    requires org.kordamp.ikonli.javafx;
//    requires org.kordamp.bootstrapfx.core;
//    requires eu.hansolo.tilesfx;
//    requires com.almasb.fxgl.all;

    opens oop.ticketcenter.ui to javafx.fxml, spring.core;
    exports oop.ticketcenter.ui;
    exports oop.ticketcenter.ui.controllers;
}