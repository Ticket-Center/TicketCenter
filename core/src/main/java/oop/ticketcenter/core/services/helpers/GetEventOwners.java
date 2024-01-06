package oop.ticketcenter.core.services.helpers;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.persistence.entities.EventOwner;
import oop.ticketcenter.persistence.repositories.EventOwnerRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class GetEventOwners {

    private final EventOwnerRepository eventOwnerRepository;
    private final List<String> owners = new ArrayList<>();
    private final Set<EventOwner> allOwners=new HashSet<>();
    public List<String> getOwners() {
        owners.clear();
        owners.addAll(eventOwnerRepository.findAll().stream()
                .map(EventOwner::getUsername).toList());
        return owners;
    }

    public Set<EventOwner> getAllOwners(){
        allOwners.clear();
        allOwners.addAll(eventOwnerRepository.findAll());
        return allOwners;
    }
}