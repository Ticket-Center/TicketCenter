package oop.ticketcenter.core.services.implementations;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.exceptions.EventDoesNotExistException;
import oop.ticketcenter.core.exceptions.IncorrectInputException;
import oop.ticketcenter.core.exceptions.UserNotFoundException;
import oop.ticketcenter.core.interfaces.events.create.CreateEvent;
import oop.ticketcenter.core.interfaces.events.create.CreateEventInput;
import oop.ticketcenter.core.interfaces.events.create.CreateEventResult;
import oop.ticketcenter.core.interfaces.events.edit.EditEvent;
import oop.ticketcenter.core.interfaces.events.edit.EditEventInput;
import oop.ticketcenter.core.interfaces.events.edit.EditEventResult;
import oop.ticketcenter.core.interfaces.events.edit.SeatTypes;
import oop.ticketcenter.persistence.entities.*;
import oop.ticketcenter.persistence.repositories.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EditEventCore implements EditEvent {
    private final EventRepository eventRepository;
    private final EventSellerRepository eventSellerRepository;

    @Override
    public EditEventResult process(EditEventInput input) {
        Event event = eventRepository.findEventByTitle(input.getOldTitle())
                .orElseThrow(() -> new EventDoesNotExistException("Event with this title not found"));
        event.setTitle(input.getNewTitle());
        event.setMaxTicketsPerPerson(input.getMaxTicketsPerPerson());
        eventRepository.save(event);
        List<String> sellers = eventSellerRepository.findEventSellerByEvents(event)
                .stream()
                .map(seller -> seller.getUsername())
                .collect(Collectors.toList());
        return EditEventResult.builder()
                .maxTicketsPerPerson(event.getMaxTicketsPerPerson())
                .title(event.getTitle())
                .eventGenre(event.getEventGenre().getName())
                .eventType(event.getEventType().getName())
                .eventOwnerUsername(event.getEventOwner().getUsername())
                .eventOrganizatorUsername(event.getEventOrganizator().getUsername())
                .eventPlace(event.getEventPlace().getName())
                .eventSellers(sellers)
                .soldTickets(event.getSoldTickets())
                .build();
    }
}
