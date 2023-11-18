package oop.ticketcenter.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

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
    @Length(min = 16, max = 16, message = "Invalid id length")
    private UUID id;

    @Length(max=80, message = "Name should be max 80 characters")
    private String name;

    @Length(min = 8, max = 255, message = "Password should be between 8 and 255 characters")
    private String password;

    @Length(min=10,max=10, message = "Uic should be 10 characters")
    private String uic;

    @Length(min=10,max=10, message = "Mol should be 10 characters")
    private String mol;

    private Double fee;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Rating rating;

    @Length(min = 10, max = 10, message = "Mol phone should be 10 characters")
    private String molPhone;

    @ManyToMany
    @JoinTable(
            name = "Event_EventSellers",
            joinColumns = @JoinColumn(name = "EventSellers_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "Events_id", referencedColumnName = "id")
    )
    private List<Event> events;
}
