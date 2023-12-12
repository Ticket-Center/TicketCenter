package oop.ticketcenter.core.services;

import oop.ticketcenter.core.exceptions.IncorrectInputException;
import oop.ticketcenter.core.interfaces.events.create.CreateEvent;
import oop.ticketcenter.core.interfaces.events.create.CreateEventInput;
import oop.ticketcenter.core.interfaces.events.create.CreateEventResult;
import oop.ticketcenter.persistence.entities.Event;
import oop.ticketcenter.persistence.entities.EventSeller;

public class CreateEventCore implements CreateEvent {
    @Override
    public CreateEventResult process(CreateEventInput input) {
        if(input.getEventGenre().isEmpty() || input.getEventPlace().isEmpty() ||
        input.getEventType().isEmpty() || input.getTitle().isEmpty() ||
        input.getEventOwnerUsername().isEmpty() || input.getEventOrganizatorUsername().isEmpty() ||
        input.getMaxTicketsPerPerson() == 0){
            throw new IncorrectInputException("Not all required fields have values");
        }
      //  EventSeller seller =

//        Event event = Event.builder()
//                .eventPlace(input.getEventPlace())
//                .eventOrganizator()
//                .build()
        return null;
    }
}
