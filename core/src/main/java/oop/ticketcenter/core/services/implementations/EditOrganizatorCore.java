package oop.ticketcenter.core.services.implementations;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.exceptions.IncorrectInputException;
import oop.ticketcenter.core.exceptions.UserAlreadyExistsException;
import oop.ticketcenter.core.exceptions.UserNotFoundException;
import oop.ticketcenter.core.interfaces.administration.organizators.edit.EditOrganizator;
import oop.ticketcenter.core.interfaces.administration.organizators.edit.EditOrganizatorInput;
import oop.ticketcenter.core.interfaces.administration.organizators.edit.EditOrganizatorResult;
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
public class EditOrganizatorCore implements EditOrganizator {
   private final EventOrganizatorRepository eventOrganizatorRepository;
    @Override
    public EditOrganizatorResult process(EditOrganizatorInput input) {
        EventOrganizator organizator = eventOrganizatorRepository.findEventOrganizatorByUsername(input.getUsername())
                .orElseThrow(()-> new UserNotFoundException("User with this username not found"));

       organizator.setFee(input.getFee());
       organizator.setName(input.getName());
       organizator.setMol(input.getMol());
       organizator.setMolPhone(input.getMolPhone());

        eventOrganizatorRepository.save(organizator);
        return EditOrganizatorResult.builder()
                .str("Event organizator edites successfully")
                .build();
    }
}
