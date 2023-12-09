package oop.ticketcenter.core.services;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.exceptions.IncorrectInputException;
import oop.ticketcenter.core.exceptions.UserAlreadyExistsException;
import oop.ticketcenter.core.interfaces.register.Register;
import oop.ticketcenter.core.interfaces.register.RegisterInput;
import oop.ticketcenter.core.interfaces.register.RegisterResult;
import oop.ticketcenter.persistence.entities.Client;
import oop.ticketcenter.persistence.enums.Roles;
import oop.ticketcenter.persistence.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterCore implements Register {
    private final ClientRepository clientRepository;
    @Override
    public RegisterResult process(RegisterInput input) {
        Optional<Client> existingClient=clientRepository.findClientByUsername(input.getUsername());
        if(existingClient.isPresent()){
            throw new UserAlreadyExistsException("User with this username already exists");
        }
        if(!input.getPassword().equals(input.getConfirmPassword())){
            throw new IncorrectInputException("Passwords don't match");
        }

        Client client=Client.builder()
                .firstname(input.getFirstName())
                .lastname(input.getLastName())
                .username(input.getUsername())
                .password(input.getPassword())
                .phone(input.getPhone())
                .address(input.getAddress())
                .passwordKey(input.getPasswordKey())
                .role(Roles.CLIENT)
                .build();
        clientRepository.save(client);
        return RegisterResult.builder()
                .str("User successfully registered")
                .build();
    }
}
