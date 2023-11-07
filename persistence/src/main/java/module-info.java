module oop.ticketcenter.persistence {
    requires jakarta.persistence;
    requires hibernate.annotations;
    requires hibernate.core;
    requires org.hibernate.validator;
    requires lombok;

    exports oop.ticketcenter.persistence;
}