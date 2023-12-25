package oop.ticketcenter.persistence.repositories;

import oop.ticketcenter.persistence.entities.Event;
import oop.ticketcenter.persistence.entities.EventPlace;
import oop.ticketcenter.persistence.entities.SeatType;
import oop.ticketcenter.persistence.entities.SoldTickets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SoldTicketsRepository extends JpaRepository<SoldTickets, UUID> {
    //Optional<SoldTickets> findSoldTicketsByEventPlaceAndAndSeatType(EventPlace eventPlace, UUID seatType);

    Optional<SoldTickets> findSoldTicketsBySeatTypeAndEvent(SeatType type, Event event);
}