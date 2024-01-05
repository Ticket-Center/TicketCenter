package oop.ticketcenter.core.services.implementations;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.interfaces.administration.organizators.register.RegisterOrganizator;
import oop.ticketcenter.core.interfaces.administration.organizators.register.RegisterOrganizatorInput;
import oop.ticketcenter.core.interfaces.administration.organizators.register.RegisterOrganizatorResult;
import oop.ticketcenter.core.interfaces.administration.owners.register.RegisterOwner;
import oop.ticketcenter.core.interfaces.administration.owners.register.RegisterOwnerInput;
import oop.ticketcenter.core.interfaces.administration.owners.register.RegisterOwnerResult;
import oop.ticketcenter.core.interfaces.administration.register.RegisterAdministration;
import oop.ticketcenter.core.interfaces.administration.register.RegisterAdministrationInput;
import oop.ticketcenter.core.interfaces.administration.register.RegisterAdministrationResult;
import oop.ticketcenter.core.interfaces.administration.sellers.register.RegisterSeller;
import oop.ticketcenter.core.interfaces.administration.sellers.register.RegisterSellerInput;
import oop.ticketcenter.core.interfaces.administration.sellers.register.RegisterSellerResult;
import oop.ticketcenter.persistence.enums.Roles;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterAdministrationCore implements RegisterAdministration {
    private final RegisterOwner registerOwner;
    private final RegisterOrganizator registerOrganizator;
    private final RegisterSeller registerSeller;
    @Override
    public RegisterAdministrationResult process(RegisterAdministrationInput input) {
        RegisterAdministrationResult administrationResult = RegisterAdministrationResult.builder().build();
        if(input.getRole().equals(Roles.OWNER.name())){
            RegisterOwnerInput ownerInput = RegisterOwnerInput.builder()
                    .name(input.getName())
                    .username(input.getUsername())
                    .password(input.getPassword())
                    .confirmPassword(input.getConfirmPassword())
                    .passwordKey(input.getPasswordKey())
                    .build();
            RegisterOwnerResult ownerResult = registerOwner.process(ownerInput);
            administrationResult.setStr(ownerResult.getStr());
        }
        if(input.getRole().equals(Roles.ORGANIZER.name())){
            RegisterOrganizatorInput organizatorInput = RegisterOrganizatorInput.builder()
                    .name(input.getName())
                    .username(input.getUsername())
                    .password(input.getPassword())
                    .confirmPassword(input.getConfirmPassword())
                    .passwordKey(input.getPasswordKey())
                    .fee(input.getFee())
                    .uic(input.getUic())
                    .molPhone(input.getMolPhone())
                    .mol(input.getMol())
                    .build();
            RegisterOrganizatorResult organizatorResult = registerOrganizator.process(organizatorInput);
            administrationResult.setStr(organizatorResult.getStr());
        }
        if(input.getRole().equals(Roles.SELLER.name())){
            RegisterSellerInput sellerInput = RegisterSellerInput.builder()
                    .name(input.getName())
                    .username(input.getUsername())
                    .password(input.getPassword())
                    .confirmPassword(input.getConfirmPassword())
                    .passwordKey(input.getPasswordKey())
                    .fee(input.getFee())
                    .uic(input.getUic())
                    .molPhone(input.getMolPhone())
                    .mol(input.getMol())
                    .build();
            RegisterSellerResult sellerResult = registerSeller.process(sellerInput);
            administrationResult.setStr(sellerResult.getStr());
        }
        return administrationResult;
    }
}
