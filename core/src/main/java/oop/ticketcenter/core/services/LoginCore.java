package oop.ticketcenter.core.services;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.exceptions.UserNotFoundException;
import oop.ticketcenter.core.interfaces.login.Login;
import oop.ticketcenter.core.interfaces.login.LoginInput;
import oop.ticketcenter.core.interfaces.login.LoginResult;
import oop.ticketcenter.persistence.entities.Client;
import oop.ticketcenter.persistence.entities.EventOrganizator;
import oop.ticketcenter.persistence.entities.EventOwner;
import oop.ticketcenter.persistence.entities.EventSeller;
import oop.ticketcenter.persistence.repositories.ClientRepository;
import oop.ticketcenter.persistence.repositories.EventOrganizatorRepository;
import oop.ticketcenter.persistence.repositories.EventOwnerRepository;
import oop.ticketcenter.persistence.repositories.EventSellerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginCore implements Login {
    private final ClientRepository clientRepository;
    private final EventOwnerRepository eventOwnerRepository;
    private final EventOrganizatorRepository eventOrganizatorRepository;
    private final EventSellerRepository eventSellerRepository;
    @Override
    public LoginResult process(LoginInput input) {

        Optional<Client> client = clientRepository.findClientByPasswordAndUsername(input.getUsername(), input.getPassword());
        Optional<EventOwner> eventOwner = eventOwnerRepository.findEventOwnerByUsernameAndPassword(input.getUsername(), input.getPassword());
        Optional<EventOrganizator> eventOrganizator = eventOrganizatorRepository.findEventOrganizatorByUsernameAndPassword(input.getUsername(), input.getPassword());
        Optional<EventSeller> eventSeller = eventSellerRepository.findEventSellerByUsernameAndPassword(input.getUsername(), input.getPassword());

        if (client.isPresent()){
            ActiveUserSingleton.getInstance().setActiveUser(client.get().getId());
        }else if(eventOwner.isPresent()){
            ActiveUserSingleton.getInstance().setActiveUser(eventOwner.get().getId());
        }else if(eventOrganizator.isPresent()){
            ActiveUserSingleton.getInstance().setActiveUser(eventOrganizator.get().getId());
        }else if(eventSeller.isPresent()){
            ActiveUserSingleton.getInstance().setActiveUser(eventSeller.get().getId());
        }else{
            throw new UserNotFoundException("User with this credentials not found");
        }
        return LoginResult.builder()
                .userId(ActiveUserSingleton.getInstance().getActiveUser())
                .build();
    }
}
