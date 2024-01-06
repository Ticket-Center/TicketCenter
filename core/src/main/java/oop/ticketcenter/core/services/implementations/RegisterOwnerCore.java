package oop.ticketcenter.core.services.implementations;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.exceptions.IncorrectInputException;
import oop.ticketcenter.core.exceptions.UserAlreadyExistsException;
import oop.ticketcenter.core.interfaces.administration.owners.register.RegisterOwner;
import oop.ticketcenter.core.interfaces.administration.owners.register.RegisterOwnerInput;
import oop.ticketcenter.core.interfaces.administration.owners.register.RegisterOwnerResult;
import oop.ticketcenter.persistence.entities.EventOwner;
import oop.ticketcenter.persistence.enums.Roles;
import oop.ticketcenter.persistence.repositories.EventOwnerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterOwnerCore implements RegisterOwner {
   private final EventOwnerRepository eventOwnerRepository;
    @Override
    public RegisterOwnerResult process(RegisterOwnerInput input) {
        Optional<EventOwner> existingOwner = eventOwnerRepository.findEventOwnerByUsername(input.getUsername());
        if(existingOwner.isPresent()){
            throw new UserAlreadyExistsException("User with this username already exists");
        }
        if(!input.getPassword().equals(input.getConfirmPassword())){
            throw new IncorrectInputException("Passwords don't match");
        }
        EventOwner owner = EventOwner.builder()
                .name(input.getName())
                .username(input.getUsername())
                .role(Roles.OWNER)
                .password(input.getPassword())
                .passwordKey(input.getPasswordKey())
                .build();
        eventOwnerRepository.save(owner);
        return RegisterOwnerResult.builder()
                .str("Event owner saves successfully")
                .build();
    }
}
