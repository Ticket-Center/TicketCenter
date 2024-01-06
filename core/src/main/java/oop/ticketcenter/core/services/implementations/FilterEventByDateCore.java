package oop.ticketcenter.core.services.implementations;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.exceptions.EventDoesNotExistException;
import oop.ticketcenter.core.interfaces.events.filter.bydate.FilterEventByDate;
import oop.ticketcenter.core.interfaces.events.filter.bydate.FilterEventByDateInput;
import oop.ticketcenter.core.interfaces.events.filter.bydate.FilterEventByDateResult;
import oop.ticketcenter.core.interfaces.tickets.get.byevent.GetTicketByEvent;
import oop.ticketcenter.core.interfaces.tickets.get.byevent.GetTicketByEventInput;
import oop.ticketcenter.core.interfaces.tickets.get.byevent.GetTicketByEventResult;
import oop.ticketcenter.core.interfaces.tickets.get.byevent.TicketByEvent;
import oop.ticketcenter.core.services.helpers.GetEvents;
import oop.ticketcenter.persistence.entities.Event;
import oop.ticketcenter.persistence.entities.PlaceSeatType;
import oop.ticketcenter.persistence.repositories.EventRepository;
import oop.ticketcenter.persistence.repositories.EventSeatPriceRepository;
import oop.ticketcenter.persistence.repositories.PlaceSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilterEventByDateCore implements FilterEventByDate {
    private final EventRepository eventRepository;
    private final GetEvents getEvents;

    @Override
    public FilterEventByDateResult process(FilterEventByDateInput input) {

        Set<Event> events = input.getEvents().stream()
                .filter(event ->
                        event.getDate().after(Date.valueOf(input.getStartDate())) && event.getDate().before(Date.valueOf(input.getEndDate()))
                        || input.getEndDate().equals(input.getStartDate()) && event.getDate().toLocalDateTime().isEqual(input.getStartDate().atStartOfDay())
                ).collect(Collectors.toSet());
        return FilterEventByDateResult.builder()
                .events(events)
                .build();
    }
}
