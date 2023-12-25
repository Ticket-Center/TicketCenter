package oop.ticketcenter.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EventSeatPrices")
public class EventSeatPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Double price;

    @ManyToOne
    private PlaceSeatType placeSeatType;

    @ManyToOne
    private Event event;
}
