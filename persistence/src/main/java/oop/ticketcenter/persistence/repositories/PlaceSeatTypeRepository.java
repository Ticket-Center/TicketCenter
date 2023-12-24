package oop.ticketcenter.persistence.repositories;

import oop.ticketcenter.persistence.entities.EventPlace;
import oop.ticketcenter.persistence.entities.PlaceSeatType;
import oop.ticketcenter.persistence.entities.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PlaceSeatTypeRepository extends JpaRepository<PlaceSeatType, UUID> {
    Optional<PlaceSeatType> findPlaceSeatTypeByEventPlaceAndSeatType(EventPlace place, SeatType seatType);
}
