package oop.ticketcenter.persistence;

import jakarta.persistence.*;

import java.util.UUID;

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
