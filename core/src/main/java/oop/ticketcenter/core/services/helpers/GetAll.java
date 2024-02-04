package oop.ticketcenter.core.services.helpers;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.persistence.repositories.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class GetAll {
    private final AdminRepository adminRepository;
    private final EventOrganizatorRepository eventOrganizatorRepository;
    private final EventSellerRepository eventSellerRepository;
    private final EventOwnerRepository eventOwnerRepository;
    private final ClientRepository clientRepository;

    public Set<Object> getAllUsers() {
        Set<Object> allUsers = new HashSet<>();

        allUsers.addAll(adminRepository.findAll());
        allUsers.addAll(eventOrganizatorRepository.findAll());
        allUsers.addAll(eventSellerRepository.findAll());
        allUsers.addAll(eventOwnerRepository.findAll());
        allUsers.addAll(clientRepository.findAll());

        return allUsers;
    }
}
