module persistence {
    requires java.base;
    requires jakarta.persistence;
    requires org.hibernate.validator;
    requires lombok;
    requires spring.data.jpa;
    requires org.hibernate.orm.core;

    opens oop.ticketcenter.persistence.entities;
    opens oop.ticketcenter.persistence.repositories;
    exports oop.ticketcenter.persistence.entities;
    exports oop.ticketcenter.persistence.repositories;

}