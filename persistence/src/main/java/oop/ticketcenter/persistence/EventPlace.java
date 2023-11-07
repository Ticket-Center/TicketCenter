package oop.ticketcenter.persistence;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "EventPlaces")
public class EventPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
}
