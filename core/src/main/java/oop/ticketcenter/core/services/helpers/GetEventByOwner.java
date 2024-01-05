package oop.ticketcenter.core.services.helpers;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.exceptions.EventArchivatedException;
import oop.ticketcenter.core.exceptions.EventDoesNotExistException;
import oop.ticketcenter.persistence.entities.Event;
import oop.ticketcenter.persistence.repositories.EventRepository;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class GetEventByOwner {
    private final EventRepository eventRepository;
    //private final Set<Event> events = new HashSet<>();
    public Event getEvents(String oldTitle){
        //events.clear();
        Event event = eventRepository.findEventByTitle(oldTitle)
                .orElseThrow(() -> new EventDoesNotExistException("Event with this title not found"));
        if(event.getIsArchived()){
            throw new EventArchivatedException("This event is archived");
        }
        return event;
    }
}