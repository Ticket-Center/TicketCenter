package oop.ticketcenter.core.services.implementations;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.exceptions.IncorrectInputException;
import oop.ticketcenter.core.exceptions.UserAlreadyExistsException;
import oop.ticketcenter.core.interfaces.administration.organizators.register.RegisterOrganizator;
import oop.ticketcenter.core.interfaces.administration.organizators.register.RegisterOrganizatorInput;
import oop.ticketcenter.core.interfaces.administration.organizators.register.RegisterOrganizatorResult;
import oop.ticketcenter.persistence.entities.EventOrganizator;
import oop.ticketcenter.persistence.enums.Roles;
import oop.ticketcenter.persistence.repositories.EventOrganizatorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterOrganizatorCore implements RegisterOrganizator {
   private final EventOrganizatorRepository eventOrganizatorRepository;
    @Override
    public RegisterOrganizatorResult process(RegisterOrganizatorInput input) {
        Optional<EventOrganizator> existingOrganizator = eventOrganizatorRepository.findEventOrganizatorByUsername(input.getUsername());
        if(existingOrganizator.isPresent()){
            throw new UserAlreadyExistsException("User with this username already exists");
        }
        if(!input.getPassword().equals(input.getConfirmPassword())){
            throw new IncorrectInputException("Passwords don't match");
        }
        EventOrganizator owner = EventOrganizator.builder()
                .name(input.getName())
                .username(input.getUsername())
                .role(Roles.ORGANIZER)
                .password(input.getPassword())
                .passwordKey(input.getPasswordKey())
                .fee(input.getFee())
                .mol(input.getMol())
                .molPhone(input.getMolPhone())
                .uic(input.getUic())
                .build();
        eventOrganizatorRepository.save(owner);
        return RegisterOrganizatorResult.builder()
                .str("Event organizator saves successfully")
                .build();
    }
}
