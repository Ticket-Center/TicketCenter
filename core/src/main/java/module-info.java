module core {
    requires lombok;
    requires spring.beans;
    requires spring.context;
    requires persistence;

    opens oop.ticketcenter.core.services to spring.beans;
    exports oop.ticketcenter.core;
    exports oop.ticketcenter.core.interfaces.login;
    exports oop.ticketcenter.core.interfaces.register;
    exports oop.ticketcenter.core.interfaces.logout;
    exports oop.ticketcenter.core.services;
    exports oop.ticketcenter.core.exceptions;
}