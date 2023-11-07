package oop.ticketcenter.persistence;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Entity
@Table(name = "EventOrganizators")
public class EventOrganizator {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String uic;

    private String mol;

    private Double fee;
    @Length(min = 10, max = 10, message = "Invalid molPhone")
    private String molPhone;
}
