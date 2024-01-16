module core {
    requires lombok;
    requires spring.beans;
    requires spring.context;
    requires persistence;
    requires org.apache.commons.lang3;
    requires java.sql;

    exports oop.ticketcenter.core;
    exports oop.ticketcenter.core.interfaces.users.login;
    exports oop.ticketcenter.core.interfaces.users.register;
    exports oop.ticketcenter.core.interfaces.users.logout;
    exports oop.ticketcenter.core.interfaces.users.forgotpassword;
    exports oop.ticketcenter.core.interfaces.events.create;
    exports oop.ticketcenter.core.interfaces.events.delete;
    exports oop.ticketcenter.core.interfaces.events.edit;
    exports oop.ticketcenter.core.interfaces.events.filter.bydate;
    exports oop.ticketcenter.core.interfaces.tickets.get.byevent;
    exports oop.ticketcenter.core.interfaces.tickets.get.byowner;
    exports oop.ticketcenter.core.interfaces.tickets.buy;
    exports oop.ticketcenter.core.interfaces.tickets.free;
    exports oop.ticketcenter.core.interfaces.administration.register;
    exports oop.ticketcenter.core.interfaces.administration.owners.register;
    exports oop.ticketcenter.core.interfaces.administration.delete;
    exports oop.ticketcenter.core.interfaces.administration.owners.edit;
    exports oop.ticketcenter.core.interfaces.administration.organizators.register;
    exports oop.ticketcenter.core.interfaces.administration.organizators.edit;
    exports oop.ticketcenter.core.interfaces.administration.sellers.register;
    exports oop.ticketcenter.core.interfaces.administration.sellers.edit;
    exports oop.ticketcenter.core.services.implementations;
    exports oop.ticketcenter.core.exceptions;
    exports oop.ticketcenter.core.services.helpers;
    opens oop.ticketcenter.core.services.helpers to spring.beans, spring.core;
    opens oop.ticketcenter.core.services.implementations to spring.beans;
    exports oop.ticketcenter.core.interfaces.administration.edit;
    exports oop.ticketcenter.core.validator;
}