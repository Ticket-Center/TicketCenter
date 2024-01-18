package oop.ticketcenter.core.services.helpers;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.persistence.entities.EventOrganizator;
import oop.ticketcenter.persistence.entities.EventOwner;
import oop.ticketcenter.persistence.entities.EventSeller;
import oop.ticketcenter.persistence.repositories.EventOrganizatorRepository;
import oop.ticketcenter.persistence.repositories.EventOwnerRepository;
import oop.ticketcenter.persistence.repositories.EventSellerRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetAllUsers {

    private final EventOrganizatorRepository eventOrganizatorRepository;
    private final EventSellerRepository eventSellerRepository;
    private final EventOwnerRepository eventOwnerRepository;
    private final List<String> users = new ArrayList<>();
    public List<String> getUsers() {
        users.clear();
        users.addAll(eventOrganizatorRepository.findAll().stream().map(EventOrganizator::getUsername).toList());
        users.addAll(eventOwnerRepository.findAll().stream().map(EventOwner::getUsername).collect(Collectors.toSet()));
        users.addAll(eventSellerRepository.findAll().stream().map(EventSeller::getUsername).collect(Collectors.toList()));
        return users;
    }
}