package oop.ticketcenter.core.services.implementations;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.interfaces.administration.edit.EditAdministration;
import oop.ticketcenter.core.interfaces.administration.edit.EditAdministrationInput;
import oop.ticketcenter.core.interfaces.administration.edit.EditAdministrationResult;
import oop.ticketcenter.core.interfaces.administration.organizators.edit.EditOrganizator;
import oop.ticketcenter.core.interfaces.administration.organizators.edit.EditOrganizatorInput;
import oop.ticketcenter.core.interfaces.administration.organizators.edit.EditOrganizatorResult;
import oop.ticketcenter.core.interfaces.administration.organizators.register.RegisterOrganizatorInput;
import oop.ticketcenter.core.interfaces.administration.organizators.register.RegisterOrganizatorResult;
import oop.ticketcenter.core.interfaces.administration.owners.edit.EditOwner;
import oop.ticketcenter.core.interfaces.administration.owners.edit.EditOwnerInput;
import oop.ticketcenter.core.interfaces.administration.owners.edit.EditOwnerResult;
import oop.ticketcenter.core.interfaces.administration.sellers.edit.EditSeller;
import oop.ticketcenter.core.interfaces.administration.sellers.edit.EditSellerInput;
import oop.ticketcenter.core.interfaces.administration.sellers.edit.EditSellerResult;
import oop.ticketcenter.persistence.enums.Roles;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditAdministrationCore implements EditAdministration {
    private final EditOwner editOwner;
    private final EditOrganizator editOrganizator;
    private final EditSeller editSeller;
    @Override
    public EditAdministrationResult process(EditAdministrationInput input) {
        EditAdministrationResult administrationResult = EditAdministrationResult.builder().build();
        if(input.getRole().equals(Roles.OWNER.name())){
            EditOwnerInput ownerInput = EditOwnerInput.builder()
                    .name(input.getName())
                    .username(input.getUsername())
                    .build();
            EditOwnerResult ownerResult = editOwner.process(ownerInput);
            administrationResult.setStr(ownerResult.getStr());
        }
        if(input.getRole().equals(Roles.ORGANIZER.name())){
            EditOrganizatorInput organizatorInput = EditOrganizatorInput.builder()
                    .name(input.getName())
                    .username(input.getUsername())
                    .fee(input.getFee())
                    .molPhone(input.getMolPhone())
                    .mol(input.getMol())
                    .build();
            EditOrganizatorResult organizatorResult = editOrganizator.process(organizatorInput);
            administrationResult.setStr(organizatorResult.getStr());
        }
        if(input.getRole().equals(Roles.SELLER.name())){
            EditSellerInput sellerInput = EditSellerInput.builder()
                    .name(input.getName())
                    .username(input.getUsername())
                    .fee(input.getFee())
                    .molPhone(input.getMolPhone())
                    .mol(input.getMol())
                    .build();
            EditSellerResult sellerResult = editSeller.process(sellerInput);
            administrationResult.setStr(sellerResult.getStr());
        }
        return administrationResult;
    }
}
