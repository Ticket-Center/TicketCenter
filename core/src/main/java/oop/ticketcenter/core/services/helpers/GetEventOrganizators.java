package oop.ticketcenter.core.services.helpers;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.persistence.entities.EventOrganizator;
import oop.ticketcenter.persistence.repositories.EventOrganizatorRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class GetEventOrganizators {

    private final EventOrganizatorRepository eventOrganizatorRepository;
    private final List<String> organizators = new ArrayList<>();
    private final Set<EventOrganizator> allOrganizators =new HashSet<>();
    public List<String> getOrganizators() {
        organizators.clear();
        organizators.addAll(eventOrganizatorRepository.findAll().stream()
                .map(EventOrganizator::getUsername).toList());
        return organizators;
    }
    public Set<EventOrganizator> getAllOrganizators() {
        allOrganizators.clear();
        allOrganizators.addAll(eventOrganizatorRepository.findAll());
        return allOrganizators;
    }
}