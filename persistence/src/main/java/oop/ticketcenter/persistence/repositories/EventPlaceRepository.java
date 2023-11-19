package oop.ticketcenter.persistence.repositories;

import oop.ticketcenter.persistence.entities.EventPlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventPlaceRepository extends JpaRepository<EventPlace, UUID> {
}