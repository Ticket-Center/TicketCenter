package oop.ticketcenter.core.services.helpers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import oop.ticketcenter.persistence.entities.Event;
import oop.ticketcenter.persistence.repositories.EventRepository;
import oop.ticketcenter.persistence.repositories.EventTypeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GetEvents {

    private final EventRepository eventRepository;
    private final EventTypeRepository eventTypeRepository;
    @Getter
    private final Set<Event> events = new HashSet<>();

    public Set<Event> fetchAllEvents() {
        events.clear();
        events.addAll(eventRepository.findAll());
        return events;
    }

}
