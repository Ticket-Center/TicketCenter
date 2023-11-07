package oop.ticketcenter.persistence;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "Events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Integer maxTicketsPerPerson;

    private Integer soldTickets;

    // to generate from database
    private Timestamp date;

    private String title;

    @ManyToOne
    private EventGenre eventGenre;

    @ManyToOne
    private EventType eventType;

    @ManyToOne
    private EventOwner eventOwner;

    @ManyToOne
    private EventOrganizator eventOrganizator;

    @ManyToOne
    private EventPlace eventPlace;
}
