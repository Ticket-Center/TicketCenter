package oop.ticketcenter.core.services;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.exceptions.IncorrectInputException;
import oop.ticketcenter.core.exceptions.UserNotFoundException;
import oop.ticketcenter.core.interfaces.forgotpassword.ForgotPassword;
import oop.ticketcenter.core.interfaces.forgotpassword.ForgotPasswordInput;
import oop.ticketcenter.core.interfaces.forgotpassword.ForgotPasswordResult;
import oop.ticketcenter.persistence.entities.Client;
import oop.ticketcenter.persistence.repositories.ClientRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForgotPasswordCore implements ForgotPassword {
    private final ClientRepository clientRepository;
    @Override
    public ForgotPasswordResult process(ForgotPasswordInput input) {
        Client client = clientRepository.findById(ActiveUserSingleton.getInstance().getActiveUser())
                .orElseThrow(() -> new UserNotFoundException("User with this credentials not found"));
        if(!input.getPasswordkey().equals(client.getPasswordKey())){
            throw new IncorrectInputException("Incorrect input");
        }
        return ForgotPasswordResult.builder()
                .newPassword(generateTempPass())
                .build();
    }
    private String generateTempPass(){
        return RandomStringUtils.randomAlphanumeric(8);
    }
}
