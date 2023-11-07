package oop.ticketcenter.persistence;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "SeatTypes")
public class SeatType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String type;
    private Integer quantity;
    @ManyToOne
    private EventPlace eventPlace;
}
