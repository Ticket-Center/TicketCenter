package oop.ticketcenter.persistence.repositories;

import oop.ticketcenter.persistence.entities.EventPlace;
import oop.ticketcenter.persistence.entities.PlaceSeatType;
import oop.ticketcenter.persistence.entities.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlaceSeatTypeRepository extends JpaRepository<PlaceSeatType, UUID> {
    Optional<PlaceSeatType> findPlaceSeatTypeByEventPlaceAndSeatType(EventPlace place, SeatType seatType);

    List<PlaceSeatType> findPlaceSeatTypeByEventPlace(EventPlace place);

    List<PlaceSeatType> findPlaceSeatTypeByEventPlace_Name(String placeName);
}
