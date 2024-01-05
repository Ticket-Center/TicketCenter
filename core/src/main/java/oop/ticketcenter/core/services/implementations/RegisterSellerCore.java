package oop.ticketcenter.core.services.implementations;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.exceptions.IncorrectInputException;
import oop.ticketcenter.core.exceptions.UserAlreadyExistsException;
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
public class RegisterSellerCore implements RegisterSeller {
   private final EventSellerRepository eventSellerRepository;
    @Override
    public RegisterSellerResult process(RegisterSellerInput input) {
        Optional<EventSeller> existingSeller = eventSellerRepository.findEventSellerByUsername(input.getUsername());
        if(existingSeller.isPresent()){
            throw new UserAlreadyExistsException("User with this username already exists");
        }
        if(!input.getPassword().equals(input.getConfirmPassword())){
            throw new IncorrectInputException("Passwords don't match");
        }
        EventSeller owner = EventSeller.builder()
                .name(input.getName())
                .username(input.getUsername())
                .role(Roles.SELLER)
                .rating(Rating.ONE_STAR)
                .password(input.getPassword())
                .passwordKey(input.getPasswordKey())
                .fee(input.getFee())
                .mol(input.getMol())
                .molPhone(input.getMolPhone())
                .uic(input.getUic())
                .build();
        eventSellerRepository.save(owner);
        return RegisterSellerResult.builder()
                .str("Event seller saves successfully")
                .build();
    }
}
