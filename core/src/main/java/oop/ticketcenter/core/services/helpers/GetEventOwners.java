package oop.ticketcenter.core.services.helpers;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.persistence.repositories.EventOwnerRepository;
import oop.ticketcenter.persistence.repositories.EventPlaceRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetEventOwners {

    private final EventOwnerRepository eventOwnerRepository;
    private final List<String> owners = new ArrayList<>();
    public List<String> getOwners() {
        owners.clear();
        owners.addAll(eventOwnerRepository.findAll().stream().map(eventOwner -> eventOwner.getUsername()).toList());
        return owners;
    }
}