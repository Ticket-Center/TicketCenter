package oop.ticketcenter.persistence.repositories;

import oop.ticketcenter.persistence.entities.EventPlace;
import oop.ticketcenter.persistence.entities.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EventTypeRepository extends JpaRepository<EventType, UUID> {
    Optional<EventType> findEventTypeByName(String type);
}