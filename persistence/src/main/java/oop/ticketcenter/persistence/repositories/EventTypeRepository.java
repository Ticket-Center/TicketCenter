package oop.ticketcenter.persistence.repositories;

import oop.ticketcenter.persistence.entities.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventTypeRepository extends JpaRepository<EventType, UUID> {
}