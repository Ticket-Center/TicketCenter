package oop.ticketcenter.core.services.implementations;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.exceptions.*;
import oop.ticketcenter.core.interfaces.tickets.buy.BuyTicket;
import oop.ticketcenter.core.interfaces.tickets.buy.BuyTicketInput;
import oop.ticketcenter.core.interfaces.tickets.buy.BuyTicketResult;
import oop.ticketcenter.core.services.helpers.ActiveUserSingleton;
import oop.ticketcenter.persistence.entities.*;
import oop.ticketcenter.persistence.entities.EventSeatPrice;
import oop.ticketcenter.persistence.repositories.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuyTicketCore implements BuyTicket {
    private final TicketRepository ticketRepository;
    private final EventPlaceRepository eventPlaceRepository;
    private final SeatTypeRepository seatTypeRepository;
    private final EventRepository eventRepository;
    private final SoldTicketsRepository soldTicketsRepository;
    private final ClientRepository clientRepository;
    private final EventSeatPriceRepository eventSeatPriceRepository;
    private final PlaceSeatTypeRepository placeSeatTypeRepository;

    @Override
    public BuyTicketResult process(BuyTicketInput input) {
        Event event = eventRepository.findEventByTitle(input.getEventTitle())
                .orElseThrow(() -> new EventDoesNotExistException("Event with this title does not exist"));
        SeatType seat=seatTypeRepository.findSeatTypeByType(input.getTicketType())
                .orElseThrow(()->new SeatTypeDoesNotExistException("Seat type does not exist"));
        PlaceSeatType place = placeSeatTypeRepository.findPlaceSeatTypeByEventPlaceAndSeatType(event.getEventPlace(), seat)
                .orElseThrow(() -> new SeatTypeDoesNotExistException("Place Seat type does not exist"));
        EventSeatPrice eventSeatPrice = eventSeatPriceRepository.findEventSeatPriceByEventAndPlaceSeatType(event, place)
                .orElseThrow(() -> new SeatTypeDoesNotExistException("Event Seat price does not exist"));
        SoldTickets sold = soldTicketsRepository.findSoldTicketsBySeatTypeAndEvent(place, event)
                .orElseThrow(() -> new SoldTicketsNotFoundException("Sold tickets not found"));

        int quantityLeft = place.getQuantity() - sold.getQuantity();
        if(quantityLeft < input.getNumberTickets()){
            throw new NotEnoughTicketsException("Not enough tickets for this event");
        }
        event.setSoldTickets(event.getSoldTickets() + input.getNumberTickets());
        eventRepository.save(event);

        sold.setQuantity(sold.getQuantity() + input.getNumberTickets());
        soldTicketsRepository.save(sold);

        Ticket ticket = Ticket.builder()
                .quantity(input.getNumberTickets())
                .eventSeatPrice(eventSeatPrice)
                .isActive(true)
                .build();
        ticketRepository.save(ticket);
        Client client = clientRepository.findClientById(ActiveUserSingleton.getInstance().getActiveUser())
                .orElseThrow(() -> new UserNotFoundException("Could not find the user"));
        client.getTickets().add(ticket);
        clientRepository.save(client);
        return BuyTicketResult.builder()
                .ticketsId(ticket.getId())
                .build();
    }
}
