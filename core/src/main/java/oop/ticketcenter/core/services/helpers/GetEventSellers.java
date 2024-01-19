package oop.ticketcenter.core.services.helpers;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.persistence.entities.EventSeller;
import oop.ticketcenter.persistence.repositories.EventSellerRepository;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetEventSellers {

    private final EventSellerRepository eventSellerRepository;
    private final List<String> sellers = new ArrayList<>();
    private final Set<EventSeller> allSellers=new HashSet<>();
    public List<String> getSellers() {
        sellers.clear();
        sellers.addAll(eventSellerRepository.findAll().stream()
                .map(EventSeller::getUsername)
                .collect(Collectors.toSet()));
        return sellers;
    }
    public Set<EventSeller> getAllSellers(){
        allSellers.clear();
        allSellers.addAll(eventSellerRepository.findAll());
        return allSellers;
    }

    public Set<EventSeller> getSellersByEventIds(Set<UUID> eventIds) {
        return eventSellerRepository.findByEvents_IdIn(eventIds);
    }
}
