package oop.ticketcenter.persistence.repositories;

import oop.ticketcenter.persistence.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    Optional<Event> findEventByTitle(String title);
    Set<Event> findAllByEventOwner_Username(String eventOwnerUsername);
}