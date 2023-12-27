package oop.ticketcenter.core.services.implementations;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.exceptions.EventDoesNotExistException;
import oop.ticketcenter.core.interfaces.tickets.get.byevent.GetTicketByEvent;
import oop.ticketcenter.core.interfaces.tickets.get.byevent.GetTicketByEventInput;
import oop.ticketcenter.core.interfaces.tickets.get.byevent.GetTicketByEventResult;
import oop.ticketcenter.core.interfaces.tickets.get.byevent.TicketByEvent;
import oop.ticketcenter.persistence.entities.Event;
import oop.ticketcenter.persistence.entities.EventSeatPrice;
import oop.ticketcenter.persistence.entities.PlaceSeatType;
import oop.ticketcenter.persistence.entities.Ticket;
import oop.ticketcenter.persistence.repositories.EventRepository;
import oop.ticketcenter.persistence.repositories.EventSeatPriceRepository;
import oop.ticketcenter.persistence.repositories.PlaceSeatTypeRepository;
import oop.ticketcenter.persistence.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetTicketsByEventCore implements GetTicketByEvent {
    private final EventRepository eventRepository;
    private final PlaceSeatTypeRepository placeSeatTypeRepository;
    private final EventSeatPriceRepository eventSeatPriceRepository;

    @Override
    public GetTicketByEventResult process(GetTicketByEventInput input) {
        GetTicketByEventResult result = GetTicketByEventResult.builder().build();
        input.getEventTitles().forEach(eventTitle ->{
            Event event = eventRepository.findEventByTitle(eventTitle)
                    .orElseThrow(() -> new EventDoesNotExistException("Event with this title does not exist"));
            List<PlaceSeatType> placeSeatType = placeSeatTypeRepository.findPlaceSeatTypeByEventPlace(event.getEventPlace());
            Integer maxSeats = 0;
            for (PlaceSeatType place : placeSeatType) {
                maxSeats += place.getQuantity();
            }

            TicketByEvent ticketByEvent = TicketByEvent.builder()
                    .maxTickets(maxSeats)
                    .soldTickets(event.getSoldTickets())
                    .build();
            result.getTicketsByEvents().add(ticketByEvent);
        });

        return result;
    }
}
