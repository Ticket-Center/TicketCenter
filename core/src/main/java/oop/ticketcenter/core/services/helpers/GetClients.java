package oop.ticketcenter.core.services.helpers;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.persistence.entities.Client;
import oop.ticketcenter.persistence.repositories.ClientRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class GetClients {
    private final ClientRepository clientRepository;
    private final Set<Client> clients=new HashSet<>();
    public Set<Client> getClients() {
        clients.clear();
        clients.addAll(clientRepository.findAll());
        return clients;
    }
}
