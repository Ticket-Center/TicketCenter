package oop.ticketcenter.core.services.helpers;

import lombok.Getter;
import oop.ticketcenter.persistence.entities.Event;
import oop.ticketcenter.persistence.repositories.EventRepository;

import java.util.Set;

public class GetEventByOwner {

    private static GetEventByOwner getEventByOwner = null;

    private EventRepository eventRepository;

    @Getter
    private final Set<Event> events;

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
