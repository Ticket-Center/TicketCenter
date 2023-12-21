package oop.ticketcenter.core.services;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.exceptions.IncorrectInputException;
import oop.ticketcenter.core.interfaces.events.create.CreateEvent;
import oop.ticketcenter.core.interfaces.events.create.CreateEventInput;
import oop.ticketcenter.core.interfaces.events.create.CreateEventResult;
import oop.ticketcenter.core.interfaces.events.delete.DeleteEvent;
import oop.ticketcenter.core.interfaces.events.delete.DeleteEventInput;
import oop.ticketcenter.core.interfaces.events.delete.DeleteEventResult;
import oop.ticketcenter.persistence.entities.Event;
import oop.ticketcenter.persistence.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteEventCore implements DeleteEvent {
    private final EventRepository eventRepository;
    @Override
    public DeleteEventResult process(DeleteEventInput input) {
        Event event = eventRepository.findById(UUID.fromString(input.getEventId()))
                .orElseThrow(() -> new EventDoesNotExistException("Event with this title does not exist"));
        eventRepository.delete(event);
        return DeleteEventResult.builder().sucessfull(true).build();
    }
}
