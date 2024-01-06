package oop.ticketcenter.core.services.implementations;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.exceptions.IncorrectInputException;
import oop.ticketcenter.core.exceptions.UserAlreadyExistsException;
import oop.ticketcenter.core.exceptions.UserNotFoundException;
import oop.ticketcenter.core.interfaces.administration.owners.edit.EditOwner;
import oop.ticketcenter.core.interfaces.administration.owners.edit.EditOwnerInput;
import oop.ticketcenter.core.interfaces.administration.owners.edit.EditOwnerResult;
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
public class EditOwnerCore implements EditOwner {
   private final EventOwnerRepository eventOwnerRepository;
    @Override
    public EditOwnerResult process(EditOwnerInput input) {
        EventOwner owner = eventOwnerRepository.findEventOwnerByUsername(input.getUsername())
                .orElseThrow(()-> new UserNotFoundException("User with this username already exists"));
        owner.setName(input.getName());
        eventOwnerRepository.save(owner);
        return EditOwnerResult.builder()
                .str("Event owner edited successfully")
                .build();
    }
}
