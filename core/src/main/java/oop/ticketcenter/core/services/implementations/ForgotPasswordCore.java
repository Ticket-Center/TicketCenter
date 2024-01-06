package oop.ticketcenter.core.services.implementations;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.exceptions.IncorrectInputException;
import oop.ticketcenter.core.exceptions.UserNotFoundException;
import oop.ticketcenter.core.interfaces.users.forgotpassword.ForgotPassword;
import oop.ticketcenter.core.interfaces.users.forgotpassword.ForgotPasswordInput;
import oop.ticketcenter.core.interfaces.users.forgotpassword.ForgotPasswordResult;
import oop.ticketcenter.persistence.entities.Client;
import oop.ticketcenter.persistence.entities.EventOrganizator;
import oop.ticketcenter.persistence.entities.EventOwner;
import oop.ticketcenter.persistence.entities.EventSeller;
import oop.ticketcenter.persistence.repositories.ClientRepository;
import oop.ticketcenter.persistence.repositories.EventOrganizatorRepository;
import oop.ticketcenter.persistence.repositories.EventOwnerRepository;
import oop.ticketcenter.persistence.repositories.EventSellerRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ForgotPasswordCore implements ForgotPassword {
    private final ClientRepository clientRepository;
    private final EventSellerRepository eventSellerRepository;
    private final EventOrganizatorRepository eventOrganizatorRepository;
    private final EventOwnerRepository eventOwnerRepository;

    @Override
    public ForgotPasswordResult process(ForgotPasswordInput input) {
        String newPas = "";
        Optional<Client> client = clientRepository.findClientByUsernameAndPasswordKey(input.getUsername(), input.getPasswordkey());
        Optional<EventOwner> owner = eventOwnerRepository.findEventOwnerByUsernameAndPasswordKey(input.getUsername(), input.getPasswordkey());
        Optional<EventOrganizator> organizator = eventOrganizatorRepository.findEventOrganizatorByUsernameAndPasswordKey(input.getUsername(), input.getPasswordkey());
        Optional<EventSeller> seller = eventSellerRepository.findEventSellerByUsernameAndPasswordKey(input.getUsername(), input.getPasswordkey());

        if (client.isEmpty() && owner.isEmpty() && organizator.isEmpty() && seller.isEmpty()) {
            throw new UserNotFoundException("User with this credentials not found");
        }
        if (client.isPresent()) {
            if (!input.getPasswordkey().equals(client.get().getPasswordKey())) {
                throw new IncorrectInputException("Incorrect input");
            }
            newPas = generateTempPass();
            client.get().setPassword(newPas);
            clientRepository.save(client.get());
        }
        if (owner.isPresent()) {
            if (!input.getPasswordkey().equals(owner.get().getPasswordKey())) {
                throw new IncorrectInputException("Incorrect input");
            }
            newPas = generateTempPass();
            owner.get().setPassword(newPas);
            eventOwnerRepository.save(owner.get());
        }
        if (organizator.isPresent()) {
            if (!input.getPasswordkey().equals(organizator.get().getPasswordKey())) {
                throw new IncorrectInputException("Incorrect input");
            }
            newPas = generateTempPass();
            organizator.get().setPassword(newPas);
            eventOrganizatorRepository.save(organizator.get());
        }
        if (seller.isPresent()) {
            if (!input.getPasswordkey().equals(seller.get().getPasswordKey())) {
                throw new IncorrectInputException("Incorrect input");
            }
            newPas = generateTempPass();
            seller.get().setPassword(newPas);
            eventSellerRepository.save(seller.get());
        }

        return ForgotPasswordResult.builder()
                .newPassword(newPas)
                .build();
    }

    private String generateTempPass() {
        return RandomStringUtils.randomAlphanumeric(8);
    }
}
