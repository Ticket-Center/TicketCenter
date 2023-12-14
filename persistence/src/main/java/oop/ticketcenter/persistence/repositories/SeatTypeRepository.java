package oop.ticketcenter.persistence.repositories;

import oop.ticketcenter.persistence.entities.EventPlace;
import oop.ticketcenter.persistence.entities.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SeatTypeRepository extends JpaRepository<SeatType, UUID> {
    Optional<SeatType> findSeatTypeByTypeAndEventPlace(String type, EventPlace eventPlace);
}