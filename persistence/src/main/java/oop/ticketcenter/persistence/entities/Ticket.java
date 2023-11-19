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
@Table(name = "Tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Double price;
    private Integer quantity;
    @ManyToOne
    private SeatType seatType;
    @ManyToOne
    private Event event;
}
