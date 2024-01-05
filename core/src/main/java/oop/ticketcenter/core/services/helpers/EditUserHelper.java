package oop.ticketcenter.core.services.helpers;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.exceptions.IncorrectInputException;
import oop.ticketcenter.core.exceptions.UserNotFoundException;
import oop.ticketcenter.persistence.entities.Client;
import oop.ticketcenter.persistence.entities.EventOrganizator;
import oop.ticketcenter.persistence.entities.EventOwner;
import oop.ticketcenter.persistence.entities.EventSeller;
import oop.ticketcenter.persistence.enums.Roles;
import oop.ticketcenter.persistence.repositories.ClientRepository;
import oop.ticketcenter.persistence.repositories.EventOrganizatorRepository;
import oop.ticketcenter.persistence.repositories.EventOwnerRepository;
import oop.ticketcenter.persistence.repositories.EventSellerRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EditUserHelper {

    private final EventOwnerRepository eventOwnerRepository;
    private final EventOrganizatorRepository eventOrganizatorRepository;
    private EventSellerRepository eventSellerRepository;

    public EditUserModel getEditUser(String username) {
        Optional<EventOwner> owner = eventOwnerRepository.findEventOwnerByUsername(username);
        Optional<EventOrganizator> organizator = eventOrganizatorRepository.findEventOrganizatorByUsername(username);
        Optional<EventSeller> seller = eventSellerRepository.findEventSellerByUsername(username);

        if (owner.isEmpty() && organizator.isEmpty() && seller.isEmpty()) {
            throw new UserNotFoundException("User with this credentials not found");
        }

        EditUserModel editUserModel = EditUserModel.builder()
                .name(null)
                .username(null)
                .fee(null)
                .molPhone(null)
                .mol(null)
                .role(null)
                .build();

        if (owner.isPresent()) {
            editUserModel.setName(owner.get().getName());
            editUserModel.setUsername(owner.get().getUsername());
            editUserModel.setRole(Roles.OWNER.name());
        }
        if (organizator.isPresent()) {
            editUserModel.setName(organizator.get().getName());
            editUserModel.setUsername(organizator.get().getUsername());
            editUserModel.setFee(organizator.get().getFee());
            editUserModel.setMol(organizator.get().getMol());
            editUserModel.setMolPhone(organizator.get().getMolPhone());
            editUserModel.setRole(Roles.ORGANIZER.name());
        }
        if (seller.isPresent()) {
            editUserModel.setName(seller.get().getName());
            editUserModel.setUsername(seller.get().getUsername());
            editUserModel.setFee(seller.get().getFee());
            editUserModel.setMol(seller.get().getMol());
            editUserModel.setMolPhone(seller.get().getMolPhone());
            editUserModel.setRole(Roles.SELLER.name());
        }

        return editUserModel;
    }

}