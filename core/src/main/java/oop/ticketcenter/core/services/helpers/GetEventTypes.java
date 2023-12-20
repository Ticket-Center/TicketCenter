package oop.ticketcenter.core.services.helpers;

import lombok.Getter;
import oop.ticketcenter.persistence.repositories.EventTypeRepository;

import java.util.List;


public class GetEventTypes {
    private  EventTypeRepository eventTypeRepository;
    private static GetEventTypes getEventTypes = null;
    @Getter
    private List<String> types;

    private GetEventTypes(){
        this.types = eventTypeRepository.findAll().stream().map(eventType -> eventType.getName()).toList();
    }
    public GetEventTypes getInstance(){
        if(getEventTypes == null){
            getEventTypes = new GetEventTypes();
        }
        return getEventTypes;
    }

}
