package oop.ticketcenter.core.services.implementations;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.exceptions.IncorrectInputException;
import oop.ticketcenter.core.exceptions.UserNotFoundException;
import oop.ticketcenter.core.interfaces.events.create.CreateEvent;
import oop.ticketcenter.core.interfaces.events.create.CreateEventInput;
import oop.ticketcenter.core.interfaces.events.create.CreateEventResult;
import oop.ticketcenter.persistence.entities.*;
import oop.ticketcenter.persistence.repositories.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateEventCore implements CreateEvent {
    private final EventRepository eventRepository;
    private final EventOrganizatorRepository eventOrganizatorRepository;
    private final EventOwnerRepository eventOwnerRepository;
    private final EventSellerRepository eventSellerRepository;
    private final EventPlaceRepository eventPlaceRepository;
    private final EventGenreRepository eventGenreRepository;
    private final EventTypeRepository eventTypeRepository;
    @Override
    public CreateEventResult process(CreateEventInput input) {
        if(input.getEventGenre().isEmpty() || input.getEventPlace().isEmpty() ||
        input.getEventType().isEmpty() || input.getTitle().isEmpty() ||
        input.getEventOwnerUsername().isEmpty() || input.getEventOrganizatorUsername().isEmpty() ||
        input.getMaxTicketsPerPerson() == 0 || input.getTitle().isBlank() || input.getEventSellers().isEmpty()){
            throw new IncorrectInputException("Not all required fields have values");
        }
        EventOwner owner = eventOwnerRepository.findEventOwnerByUsername(input.getEventOwnerUsername())
                .orElseThrow( () -> new UserNotFoundException("Event owner with this username not found"));

        EventOrganizator organizator = eventOrganizatorRepository.findEventOrganizatorByUsername(input.getEventOrganizatorUsername())
                .orElseThrow(() -> new UserNotFoundException("Event organizator with this username not found"));

        EventPlace place = eventPlaceRepository.findEventPlaceByName(input.getEventPlace())
                .orElseThrow(() -> new UserNotFoundException("Event place with this name not found"));

        EventGenre genre = eventGenreRepository.findEventGenreByName(input.getEventGenre())
                .orElseThrow(() -> new UserNotFoundException("Event genre with this name not found"));

        EventType type = eventTypeRepository.findEventTypeByName(input.getEventType())
                .orElseThrow(() -> new UserNotFoundException("Event type with this name not found"));


        Event event = Event.builder()
                .eventPlace(place)
                .eventOrganizator(organizator)
                .eventGenre(genre)
                .eventType(type)
                .eventOwner(owner)
                .maxTicketsPerPerson(input.getMaxTicketsPerPerson())
                .soldTickets(0)
                .title(input.getTitle())
                .build();

        eventRepository.save(event);

        for (String eventSeller: input.getEventSellers() ) {
            Optional<EventSeller> seller = eventSellerRepository.findEventSellerByUsername(eventSeller);
            seller.ifPresent(value -> value.getEvents().add(event));
        }
        return CreateEventResult.builder()
                .id(event.getId())
                .build();
    }
}
