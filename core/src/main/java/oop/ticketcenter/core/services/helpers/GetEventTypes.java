package oop.ticketcenter.core.services.helpers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import oop.ticketcenter.persistence.repositories.EventTypeRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetEventTypes {
    private final EventTypeRepository eventTypeRepository;

    private final List<String> types = new ArrayList<>();

    public List<String> getTypes(){
        types.clear();
        types.addAll(eventTypeRepository.findAll().stream().map(eventType -> eventType.getName()).toList());
        return types;
    }

}
