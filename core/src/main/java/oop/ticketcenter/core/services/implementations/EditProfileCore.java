package oop.ticketcenter.core.services.implementations;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.interfaces.users.edit.EditProfile;
import oop.ticketcenter.core.interfaces.users.edit.EditProfileInput;
import oop.ticketcenter.core.interfaces.users.edit.EditProfileResult;
import oop.ticketcenter.core.services.helpers.ActiveUserSingleton;
import oop.ticketcenter.persistence.entities.*;
import oop.ticketcenter.persistence.repositories.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EditProfileCore implements EditProfile {
    private final ClientRepository clientRepository;
    private final EventSellerRepository eventSellerRepository;
    private final EventOrganizatorRepository eventOrganizatorRepository;
    private final EventOwnerRepository eventOwnerRepository;
    private final AdminRepository adminRepository;
    @Override
    public EditProfileResult process(EditProfileInput input) {
        String username= ActiveUserSingleton.getInstance().getUsername();

        Optional<Client> client = clientRepository.findClientByUsername(username);
        Optional<EventSeller> seller = eventSellerRepository.findEventSellerByUsername(username);
        Optional<EventOrganizator> organizer = eventOrganizatorRepository.findEventOrganizatorByUsername(username);
        Optional<EventOwner> owner = eventOwnerRepository.findEventOwnerByUsername(username);
        Optional<Admin> admin=adminRepository.findByUsername(username);

        if (admin.isPresent()){
            if(!input.getFirstName().isEmpty()) admin.get().setFirstname(input.getFirstName());
            if(!input.getLastName().isEmpty())admin.get().setLastname(input.getLastName());
            adminRepository.save(admin.get());
        }
        if(owner.isPresent()){
            if(!input.getFirstName().isEmpty()) owner.get().setName(input.getFirstName());
            if(!input.getPasswordKey().isEmpty()) owner.get().setPasswordKey(input.getPasswordKey());
            eventOwnerRepository.save(owner.get());
        }
        if(organizer.isPresent()){
            if(!input.getFirstName().isEmpty()) organizer.get().setName(input.getFirstName());
            if(!input.getPasswordKey().isEmpty()) organizer.get().setPasswordKey(input.getPasswordKey());
            eventOrganizatorRepository.save(organizer.get());
        }
        if(seller.isPresent()){
            if(!input.getFirstName().isEmpty()) seller.get().setName(input.getFirstName());
            if(!input.getPasswordKey().isEmpty()) seller.get().setPasswordKey(input.getPasswordKey());
            eventSellerRepository.save(seller.get());
        }
        if(client.isPresent()){
            if(!input.getFirstName().isEmpty()) client.get().setFirstname(input.getFirstName());
            if(!input.getLastName().isEmpty()) client.get().setLastname(input.getLastName());
            if(!input.getPasswordKey().isEmpty()) client.get().setPasswordKey(input.getPasswordKey());
            if(!input.getAddress().isEmpty()) client.get().setAddress(input.getAddress());
            if(!input.getPhone().isEmpty()) client.get().setPhone(input.getPhone());
            clientRepository.save(client.get());
        }
        return EditProfileResult.builder().successful(true).build();
    }
}
