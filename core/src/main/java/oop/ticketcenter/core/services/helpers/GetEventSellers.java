package oop.ticketcenter.core.services.helpers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import oop.ticketcenter.persistence.repositories.EventSellerRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetEventSellers {

    private final EventSellerRepository eventSellerRepository;
    private final List<String> sellers = new ArrayList<>();
    public List<String> getSellers() {
        sellers.clear();
        sellers.addAll(eventSellerRepository.findAll().stream().map(eventSeller -> eventSeller.getUsername()).collect(Collectors.toSet()));
        return sellers;
    }
}
