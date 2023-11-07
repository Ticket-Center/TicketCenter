package oop.ticketcenter.persistence;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "EventTypes")
public class EventType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
}
