package oop.ticketcenter.core.services.helpers;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.persistence.repositories.EventPlaceRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetEventPlaces {

    private final EventPlaceRepository eventPlaceRepository;
    private final List<String> places = new ArrayList<>();
    public List<String> getPlaces() {
        places.clear();
        places.addAll(eventPlaceRepository.findAll().stream().map(eventPlace -> eventPlace.getName()).toList());
        return places;
    }
}