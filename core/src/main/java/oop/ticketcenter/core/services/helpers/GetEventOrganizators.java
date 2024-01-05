package oop.ticketcenter.core.services.helpers;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.persistence.entities.SeatType;
import oop.ticketcenter.persistence.repositories.EventOrganizatorRepository;
import oop.ticketcenter.persistence.repositories.EventSellerRepository;
import oop.ticketcenter.persistence.repositories.SeatTypeRepository;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetEventOrganizators {

    private final EventOrganizatorRepository eventOrganizatorRepository;
    private final List<String> organizators = new ArrayList<>();
    public List<String> getOrganizators() {
        organizators.clear();
        organizators.addAll(eventOrganizatorRepository.findAll().stream().map(eventOrganizator -> eventOrganizator.getUsername()).toList());
        return organizators;
    }
}