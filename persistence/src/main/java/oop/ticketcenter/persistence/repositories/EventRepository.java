package oop.ticketcenter.persistence.repositories;

import oop.ticketcenter.persistence.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}