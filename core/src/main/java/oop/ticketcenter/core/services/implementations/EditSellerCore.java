package oop.ticketcenter.core.services.implementations;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.exceptions.IncorrectInputException;
import oop.ticketcenter.core.exceptions.UserAlreadyExistsException;
import oop.ticketcenter.core.exceptions.UserNotFoundException;
import oop.ticketcenter.core.interfaces.administration.sellers.edit.EditSeller;
import oop.ticketcenter.core.interfaces.administration.sellers.edit.EditSellerInput;
import oop.ticketcenter.core.interfaces.administration.sellers.edit.EditSellerResult;
import oop.ticketcenter.core.interfaces.administration.sellers.register.RegisterSeller;
import oop.ticketcenter.core.interfaces.administration.sellers.register.RegisterSellerInput;
import oop.ticketcenter.core.interfaces.administration.sellers.register.RegisterSellerResult;
import oop.ticketcenter.persistence.entities.EventSeller;
import oop.ticketcenter.persistence.enums.Rating;
import oop.ticketcenter.persistence.enums.Roles;
import oop.ticketcenter.persistence.repositories.EventSellerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EditSellerCore implements EditSeller {
   private final EventSellerRepository eventSellerRepository;
    @Override
    public EditSellerResult process(EditSellerInput input) {
        EventSeller seller = eventSellerRepository.findEventSellerByUsername(input.getUsername())
                .orElseThrow(()-> new UserNotFoundException("User with this username already exists"));

        seller.setFee(input.getFee());
        seller.setName(input.getName());
        seller.setMol(input.getMol());
        seller.setMolPhone(input.getMolPhone());
        eventSellerRepository.save(seller);
        return EditSellerResult.builder()
                .str("Event seller edited successfully")
                .build();
    }
}
