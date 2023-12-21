module core {
    requires lombok;
    requires spring.beans;
    requires spring.context;
    requires persistence;
    requires org.apache.commons.lang3;

    opens oop.ticketcenter.core.services to spring.beans;
    exports oop.ticketcenter.core;
    exports oop.ticketcenter.core.interfaces.users.login;
    exports oop.ticketcenter.core.interfaces.users.register;
    exports oop.ticketcenter.core.interfaces.users.logout;
    exports oop.ticketcenter.core.interfaces.users.forgotpassword;
    exports oop.ticketcenter.core.interfaces.events.create;
    exports oop.ticketcenter.core.interfaces.events.delete;
    exports oop.ticketcenter.core.interfaces.events.edit;
    exports oop.ticketcenter.core.interfaces.tickets.get.byevent;
    exports oop.ticketcenter.core.interfaces.tickets.get.byowner;
    exports oop.ticketcenter.core.interfaces.tickets.buy;
    exports oop.ticketcenter.core.interfaces.tickets.free;
    exports oop.ticketcenter.core.services;
    exports oop.ticketcenter.core.exceptions;
    exports oop.ticketcenter.core.services.helpers;
    opens oop.ticketcenter.core.services.helpers to spring.beans;
    exports oop.ticketcenter.core.services.implementations;
    opens oop.ticketcenter.core.services.implementations to spring.beans;
}