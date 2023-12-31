package oop.ticketcenter.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import oop.ticketcenter.persistence.enums.Rating;
import oop.ticketcenter.persistence.enums.Roles;

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

    @Length(max=80, message = "Name should be max 80 characters")
    private String name;

    @Length(min = 8, max = 255, message = "Password should be between 8 and 255 characters")
    private String password;

    @Length(min=10,max=10, message = "Uic should be 10 characters")
    private String uic;

    //@Length(min=5, message = "Mol should be more than 10 characters")
    private String mol;

    private Double fee;
    @Length(max = 20, message = "Username must be less than {max} symbols")
    private String username;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Rating rating;

    private String passwordKey;

    @Length(min = 10, max = 10, message = "Mol phone should be 10 characters")
    private String molPhone;

    @Enumerated(value = EnumType.STRING)
    private Roles role = Roles.SELLER;

    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(
            name = "Event_EventSellers",
            joinColumns = @JoinColumn(name = "EventSellers_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "Events_id", referencedColumnName = "id")
    )
    private List<Event> events;
}
