package oop.ticketcenter.persistence.repositories;

import oop.ticketcenter.persistence.entities.Event;
import oop.ticketcenter.persistence.entities.EventSeatPrice;
import oop.ticketcenter.persistence.entities.PlaceSeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EventSeatPriceRepository extends JpaRepository<EventSeatPrice, UUID> {
    Optional<EventSeatPrice> findEventSeatPriceByEventAndPlaceSeatType(Event event, PlaceSeatType placeSeatType);
}