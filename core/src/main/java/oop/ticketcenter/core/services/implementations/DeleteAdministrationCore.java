package oop.ticketcenter.core.services.implementations;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.exceptions.EventDoesNotExistException;
import oop.ticketcenter.core.exceptions.IncorrectInputException;
import oop.ticketcenter.core.exceptions.UserNotFoundException;
import oop.ticketcenter.core.interfaces.administration.delete.DeleteAdministration;
import oop.ticketcenter.core.interfaces.administration.delete.DeleteAdministrationInput;
import oop.ticketcenter.core.interfaces.administration.delete.DeleteAdministrationResult;
import oop.ticketcenter.core.interfaces.administration.edit.EditAdministrationInput;
import oop.ticketcenter.core.interfaces.administration.edit.EditAdministrationResult;
import oop.ticketcenter.core.interfaces.events.delete.DeleteEvent;
import oop.ticketcenter.core.interfaces.events.delete.DeleteEventInput;
import oop.ticketcenter.core.interfaces.events.delete.DeleteEventResult;
import oop.ticketcenter.persistence.entities.*;
import oop.ticketcenter.persistence.repositories.EventOrganizatorRepository;
import oop.ticketcenter.persistence.repositories.EventOwnerRepository;
import oop.ticketcenter.persistence.repositories.EventRepository;
import oop.ticketcenter.persistence.repositories.EventSellerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteAdministrationCore implements DeleteAdministration {
    private final EventOwnerRepository eventOwnerRepository;
    private final EventOrganizatorRepository eventOrganizatorRepository;
    private final EventSellerRepository eventSellerRepository;
    @Override
    public DeleteAdministrationResult process(DeleteAdministrationInput input) {
       Optional<EventOwner> owner = eventOwnerRepository.findEventOwnerByUsername(input.getUsername());
       Optional<EventOrganizator> organizator = eventOrganizatorRepository.findEventOrganizatorByUsername(input.getUsername());
       Optional<EventSeller> seller = eventSellerRepository.findEventSellerByUsername(input.getUsername());
       if (owner.isEmpty() && organizator.isEmpty() && seller.isEmpty()) {
           throw new UserNotFoundException("User with this credentials not found");
       }
      if (owner.isPresent()) {
          eventOwnerRepository.delete(owner.get());
       }
       if (organizator.isPresent()) {
          eventOrganizatorRepository.delete(organizator.get());
       }
       if (seller.isPresent()) {
         eventSellerRepository.delete(seller.get());
       }
       return DeleteAdministrationResult.builder()
               .str("Delete successfully")
               .build();
    }
}
