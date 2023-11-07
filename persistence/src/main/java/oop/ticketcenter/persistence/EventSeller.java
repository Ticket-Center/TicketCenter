package oop.ticketcenter.persistence;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "EventSellers")
public class EventSeller {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String uic;
    private String mol;
    private Double fee;
    private Raiting raiting;
    private String molPhone;

    @ManyToMany
    @JoinTable(
            name = "Event_Sellers",
            joinColumns = @JoinColumn(name = "EventSellers_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "Events_id", referencedColumnName = "id")
    )
    private List<Event> events;
}
