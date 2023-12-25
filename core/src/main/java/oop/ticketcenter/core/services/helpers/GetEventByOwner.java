package oop.ticketcenter.core.services.helpers;

import lombok.Getter;
import oop.ticketcenter.persistence.entities.Event;
import oop.ticketcenter.persistence.repositories.EventRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GetEventByOwner {

    private static GetEventByOwner getEventByOwner = null;
    private EventRepository eventRepository;

    @Getter
    private Set<Event> events;

    private GetEventByOwner(){
        this.events = eventRepository.findAllByEventOwner_Username(ActiveUserSingleton.getInstance().getUsername());
    }

    public static GetEventByOwner getInstance(){
        if(getEventByOwner == null){
            getEventByOwner = new GetEventByOwner();
        }
        return getEventByOwner;
    }
}
