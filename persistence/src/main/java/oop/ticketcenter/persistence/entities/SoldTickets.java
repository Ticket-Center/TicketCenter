package oop.ticketcenter.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SoldTickets")
public class SoldTickets {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "seat_type_id")
    private PlaceSeatType seatType;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

}
