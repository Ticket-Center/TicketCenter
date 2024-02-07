package oop.ticketcenter.core.services.implementations;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.exceptions.SoldTicketsNotFoundException;
import oop.ticketcenter.core.exceptions.TicketNotFoundException;
import oop.ticketcenter.core.interfaces.tickets.free.FreeTicket;
import oop.ticketcenter.core.interfaces.tickets.free.FreeTicketInput;
import oop.ticketcenter.core.interfaces.tickets.free.FreeTicketResult;
import oop.ticketcenter.persistence.entities.*;
import oop.ticketcenter.persistence.repositories.EventRepository;
import oop.ticketcenter.persistence.repositories.SoldTicketsRepository;
import oop.ticketcenter.persistence.repositories.TicketRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FreeTicketCore implements FreeTicket {
    private final TicketRepository ticketRepository;
    private final SoldTicketsRepository soldTicketsRepository;
    private final EventRepository eventRepository;

    @Override
    public FreeTicketResult process(FreeTicketInput input) {
        Ticket ticket = ticketRepository.findById(input.getTicketId())
                .orElseThrow(() -> new TicketNotFoundException("Ticket with this id not found"));

        Event event = ticket.getEventSeatPrice().getEvent();
        PlaceSeatType seat = ticket.getEventSeatPrice().getPlaceSeatType();
        SoldTickets sold = soldTicketsRepository.findSoldTicketsBySeatTypeAndEvent(seat, event)
                .orElseThrow(() -> new SoldTicketsNotFoundException("Sold tickets not found"));

        event.setSoldTickets(event.getSoldTickets() - ticket.getQuantity());
        eventRepository.save(event);

        sold.setQuantity(sold.getQuantity() - ticket.getQuantity());
        soldTicketsRepository.save(sold);

        ticket.setIsActive(false);
        ticketRepository.save(ticket);
        return FreeTicketResult.builder().isSuccessfull(true).build();
    }
}
