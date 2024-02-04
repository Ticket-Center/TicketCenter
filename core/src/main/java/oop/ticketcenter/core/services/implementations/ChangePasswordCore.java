package oop.ticketcenter.core.services.implementations;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.exceptions.IncorrectInputException;
import oop.ticketcenter.core.exceptions.UserNotFoundException;
import oop.ticketcenter.core.interfaces.users.changePassword.ChangePassword;
import oop.ticketcenter.core.interfaces.users.changePassword.ChangePasswordInput;
import oop.ticketcenter.core.interfaces.users.changePassword.ChangePasswordResult;
import oop.ticketcenter.core.services.helpers.ActiveUserSingleton;
import oop.ticketcenter.persistence.entities.*;
import oop.ticketcenter.persistence.repositories.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChangePasswordCore implements ChangePassword {
    private final ClientRepository clientRepository;
    private final EventSellerRepository eventSellerRepository;
    private final EventOrganizatorRepository eventOrganizatorRepository;
    private final EventOwnerRepository eventOwnerRepository;
    private final AdminRepository adminRepository;

    @Override
    public ChangePasswordResult process(ChangePasswordInput input) {
        String username= ActiveUserSingleton.getInstance().getUsername();

        Optional<Client> client = clientRepository.findClientByUsername(username);
        Optional<EventSeller> seller = eventSellerRepository.findEventSellerByUsername(username);
        Optional<EventOrganizator> organizer = eventOrganizatorRepository.findEventOrganizatorByUsername(username);
        Optional<EventOwner> owner = eventOwnerRepository.findEventOwnerByUsername(username);
        Optional<Admin> admin=adminRepository.findByUsername(username);

        if (client.isEmpty() && seller.isEmpty() && organizer.isEmpty() && owner.isEmpty() && admin.isEmpty()) {
            throw new UserNotFoundException("User with this credentials not found");
        }
        if(client.isPresent()){
            if(!input.getCurrentPassword().equals(client.get().getPassword())){
                throw new IncorrectInputException("Current password is invalid");
            }
            if (!input.getNewPassword().equals(input.getConfirmPassword())) {
                throw new IncorrectInputException("Passwords don't match");
            }
            if(input.getNewPassword().isEmpty()){
                throw new IncorrectInputException("Password field is empty");
            }
            client.get().setPassword(input.getNewPassword());
            clientRepository.save(client.get());
        }
        if(seller.isPresent()){
            if(!input.getCurrentPassword().equals(seller.get().getPassword())){
                throw new IncorrectInputException("Current password is invalid");
            }
            if (!input.getNewPassword().equals(input.getConfirmPassword())) {
                throw new IncorrectInputException("Passwords don't match");
            }
            if(input.getNewPassword().isEmpty()){
                throw new IncorrectInputException("Password field is empty");
            }
            seller.get().setPassword(input.getNewPassword());
            eventSellerRepository.save(seller.get());
        }
        if(organizer.isPresent()) {
            if (!input.getCurrentPassword().equals(organizer.get().getPassword())) {
                throw new IncorrectInputException("Current password is invalid");
            }
            if (!input.getNewPassword().equals(input.getConfirmPassword())) {
                throw new IncorrectInputException("Passwords don't match");
            }
            if(input.getNewPassword().isEmpty()){
                throw new IncorrectInputException("Password field is empty");
            }
            organizer.get().setPassword(input.getNewPassword());
            eventOrganizatorRepository.save(organizer.get());
        }
        if(owner.isPresent()) {
            if (!input.getCurrentPassword().equals(owner.get().getPassword())) {
                throw new IncorrectInputException("Current password is invalid");
            }
            if (!input.getNewPassword().equals(input.getConfirmPassword())) {
                throw new IncorrectInputException("Passwords don't match");
            }
            if(input.getNewPassword().isEmpty()){
                throw new IncorrectInputException("Password field is empty");
            }
            owner.get().setPassword(input.getNewPassword());
            eventOwnerRepository.save(owner.get());
        }
        if(admin.isPresent()) {
            if (!input.getCurrentPassword().equals(admin.get().getPassword())) {
                throw new IncorrectInputException("Current password is invalid");
            }
            if (!input.getNewPassword().equals(input.getConfirmPassword())) {
                throw new IncorrectInputException("Passwords don't match");
            }
            if(input.getNewPassword().isEmpty()){
                throw new IncorrectInputException("Password field is empty");
            }
            admin.get().setPassword(input.getNewPassword());
            adminRepository.save(admin.get());
        }

        return ChangePasswordResult.builder()
                .successful(true)
                .build();
    }
}
