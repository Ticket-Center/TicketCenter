module oop.ticketcenter.TicketCenterProject {
    requires javafx.controls;
    requires javafx.fxml;
//    requires javafx.web;

//    requires org.controlsfx.controls;
//    requires com.dlsc.formsfx;
//    requires org.kordamp.ikonli.javafx;
//    requires org.kordamp.bootstrapfx.core;
//    requires eu.hansolo.tilesfx;

    opens oop.ticketcenter.TicketCenterProject to javafx.fxml;
    exports oop.ticketcenter.TicketCenterProject;
}