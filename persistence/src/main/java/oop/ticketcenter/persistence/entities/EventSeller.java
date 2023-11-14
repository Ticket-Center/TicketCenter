package oop.ticketcenter.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import oop.ticketcenter.persistence.entities.Rating;

import java.util.List;
import java.util.UUID;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Rating rating;
    private String molPhone;

    @ManyToMany
    @JoinTable(
            name = "Event_EventSellers",
            joinColumns = @JoinColumn(name = "EventSellers_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "Events_id", referencedColumnName = "id")
    )
    private List<Event> events;
}
