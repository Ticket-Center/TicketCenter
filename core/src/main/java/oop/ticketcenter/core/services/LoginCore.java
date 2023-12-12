package oop.ticketcenter.core.services;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.exceptions.IncorrectInputException;
import oop.ticketcenter.core.exceptions.UserNotFoundException;
import oop.ticketcenter.core.interfaces.users.login.Login;
import oop.ticketcenter.core.interfaces.users.login.LoginInput;
import oop.ticketcenter.core.interfaces.users.login.LoginResult;
import oop.ticketcenter.persistence.entities.*;
import oop.ticketcenter.persistence.repositories.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginCore implements Login {
    private final ClientRepository clientRepository;
    private final EventOwnerRepository eventOwnerRepository;
    private final EventOrganizatorRepository eventOrganizatorRepository;
    private final EventSellerRepository eventSellerRepository;
    private final AdminRepository adminRepository;
    @Override
    public LoginResult process(LoginInput input) {
        if(input.getUsername().isEmpty() || input.getPassword().isEmpty()){
            throw new IncorrectInputException("Username and password cannot be empty");
        }

        Optional<Client> client = clientRepository.findClientByUsernameAndPassword(input.getUsername(), input.getPassword());
        Optional<EventOwner> eventOwner = eventOwnerRepository.findEventOwnerByUsernameAndPassword(input.getUsername(), input.getPassword());
        Optional<EventOrganizator> eventOrganizator = eventOrganizatorRepository.findEventOrganizatorByUsernameAndPassword(input.getUsername(), input.getPassword());
        Optional<EventSeller> eventSeller = eventSellerRepository.findEventSellerByUsernameAndPassword(input.getUsername(), input.getPassword());
        Optional<Admin> admin = adminRepository.findAdminByUsernameAndPassword(input.getUsername(), input.getPassword());

        if (client.isPresent()){
            ActiveUserSingleton.getInstance().setActiveUser(client.get().getId());
            ActiveUserSingleton.getInstance().setUserRole(client.get().getRole());
            ActiveUserSingleton.getInstance().setUsername(client.get().getUsername());
        }else if(eventOwner.isPresent()){
            ActiveUserSingleton.getInstance().setActiveUser(eventOwner.get().getId());
            ActiveUserSingleton.getInstance().setUserRole(eventOwner.get().getRole());
            ActiveUserSingleton.getInstance().setUsername(eventOwner.get().getUsername());
        }else if(eventOrganizator.isPresent()){
            ActiveUserSingleton.getInstance().setActiveUser(eventOrganizator.get().getId());
            ActiveUserSingleton.getInstance().setUserRole(eventOrganizator.get().getRole());
            ActiveUserSingleton.getInstance().setUsername(eventOrganizator.get().getUsername());
        }else if(eventSeller.isPresent()){
            ActiveUserSingleton.getInstance().setActiveUser(eventSeller.get().getId());
            ActiveUserSingleton.getInstance().setUserRole(eventSeller.get().getRole());
            ActiveUserSingleton.getInstance().setUsername(eventSeller.get().getUsername());
        }else if(admin.isPresent()){
            ActiveUserSingleton.getInstance().setActiveUser(admin.get().getId());
            ActiveUserSingleton.getInstance().setUserRole(admin.get().getRole());
            ActiveUserSingleton.getInstance().setUsername(admin.get().getUsername());
        }else{
            throw new UserNotFoundException("User with this credentials not found");
        }
        return LoginResult.builder()
                .userId(ActiveUserSingleton.getInstance().getActiveUser())
                .roles(ActiveUserSingleton.getInstance().getUserRole())
                .build();
    }
}
