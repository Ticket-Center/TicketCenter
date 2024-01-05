package oop.ticketcenter.core.services.helpers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import oop.ticketcenter.persistence.entities.Event;
import oop.ticketcenter.persistence.entities.EventGenre;
import oop.ticketcenter.persistence.entities.EventPlace;
import oop.ticketcenter.persistence.entities.EventType;
import oop.ticketcenter.persistence.repositories.EventGenreRepository;
import oop.ticketcenter.persistence.repositories.EventPlaceRepository;
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
    private final EventGenreRepository eventGenreRepository;
    private final EventPlaceRepository eventPlaceRepository;

    @Getter
    private final Set<Event> events = new HashSet<>();

    public Set<Event> fetchAllEvents() {
        events.clear();
        events.addAll(eventRepository.findAll());
        setEventsTypeName();
        setEventsGenreName();
        setEventsPlaceName();
        return events;
    }

    private void setEventsTypeName(){
        for(Event event: events){
            if(event.getEventType()!=null){
                String name=eventTypeRepository.findById(event.getEventType().getId())
                        .map(EventType::getName)
                        .orElse(null);
                event.getEventType().setName(name);
            }
        }
    }

    private void setEventsGenreName(){
        for(Event event: events){
            if(event.getEventGenre()!=null){
                String name=eventGenreRepository.findById(event.getEventGenre().getId())
                        .map(EventGenre::getName)
                        .orElse(null);
                event.getEventGenre().setName(name);
            }
        }
    }

    private void setEventsPlaceName(){
        for(Event event: events){
            if(event.getEventPlace()!=null){
                String name=eventPlaceRepository.findById(event.getEventPlace().getId())
                        .map(EventPlace::getName)
                        .orElse(null);
                event.getEventPlace().setName(name);
            }
        }
    }
}
