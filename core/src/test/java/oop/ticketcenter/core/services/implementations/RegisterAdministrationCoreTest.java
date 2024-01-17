package oop.ticketcenter.core.services.implementations;

import oop.ticketcenter.core.interfaces.administration.organizators.register.RegisterOrganizatorInput;
import oop.ticketcenter.core.interfaces.administration.organizators.register.RegisterOrganizatorResult;
import oop.ticketcenter.core.interfaces.administration.owners.register.RegisterOwnerInput;
import oop.ticketcenter.core.interfaces.administration.owners.register.RegisterOwnerResult;
import oop.ticketcenter.core.interfaces.administration.register.RegisterAdministrationInput;
import oop.ticketcenter.core.interfaces.administration.register.RegisterAdministrationResult;
import oop.ticketcenter.core.interfaces.administration.sellers.register.RegisterSellerInput;
import oop.ticketcenter.core.interfaces.administration.sellers.register.RegisterSellerResult;
import oop.ticketcenter.core.interfaces.events.create.SeatTypes;
import oop.ticketcenter.core.interfaces.events.edit.EditEventInput;
import oop.ticketcenter.core.interfaces.events.edit.EditEventResult;
import oop.ticketcenter.persistence.entities.*;
import oop.ticketcenter.persistence.enums.Rating;
import oop.ticketcenter.persistence.enums.Roles;
import oop.ticketcenter.persistence.repositories.EventOrganizatorRepository;
import oop.ticketcenter.persistence.repositories.EventOwnerRepository;
import oop.ticketcenter.persistence.repositories.EventRepository;
import oop.ticketcenter.persistence.repositories.EventSellerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class RegisterAdministrationCoreTest {

    private final String ownerUsername = "OwnerUsername1";
    private final String ownerName = "OwnerName1";
    private final String organizerUsername = "OrganizerUsername1";
    private final String organizerName = "OrganizerName1";
    private final String organizerUIC = "147258369";
    private final Double organizerFee = 23.4;
    private final String testMol = "TestMol";
    private final String testMolPhone = "0887975774";
    private final String testPassword = "testpassword1";
    private final String testPasswordKey = "testpassword1";
    private final String sellerUsername = "SellerUsername1";
    private final String sellerName = "SellerName1";
    private final String sellerrUIC = "147258369";
    private final Double sellererFee = 23.4;

    @Mock
    private EventOwnerRepository eventOwnerRepository;

    @Mock
    private EventOrganizatorRepository eventOrganizatorRepository;

    @Mock
    private EventSellerRepository eventSellerRepository;

    @InjectMocks
    private RegisterOwnerCore registerOwnerCore;
    @InjectMocks
    private RegisterOrganizatorCore registerOrganizatorCore;
    @InjectMocks
    private RegisterSellerCore registerSellerCore;

//    @InjectMocks
//    private RegisterAdministrationCore registerAdministrationCore;

    EventOwner eventOwner;
    EventSeller eventSeller;
    EventOrganizator eventOrganizator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        eventOwner = EventOwner.builder()
                .name(ownerName)
                .username(ownerUsername)
                .password(testPassword)
                .passwordKey(testPasswordKey)
                .role(Roles.OWNER)
                .build();
        eventOwnerRepository.save(eventOwner);
        eventOrganizator = EventOrganizator.builder()
                .name(organizerName)
                .username(organizerUsername)
                .password(testPassword)
                .passwordKey(testPasswordKey)
                .role(Roles.ORGANIZER)
                .uic(organizerUIC)
                .fee(organizerFee)
                .mol(testMol)
                .molPhone(testMolPhone)
                .build();
        eventOrganizatorRepository.save(eventOrganizator);
        eventSeller = EventSeller.builder()
                .name(sellerName)
                .username(sellerUsername)
                .password(testPassword)
               .passwordKey(testPasswordKey)
                .role(Roles.SELLER)
                .uic(sellerrUIC)
                .fee(sellererFee)
                .mol(testMol)
                .molPhone(testMolPhone)
                .build();
        eventSellerRepository.save(eventSeller);
    }

    @AfterEach
    void tearDown() {
        eventSellerRepository.deleteAll();
        eventOrganizatorRepository.deleteAll();
        eventOwnerRepository.deleteAll();
     }

    @Test
    void registerOrganizerSuccesful() {
        when(eventOrganizatorRepository.findEventOrganizatorByUsername(any())).thenReturn(Optional.empty());
        RegisterOrganizatorInput input = RegisterOrganizatorInput.builder()
                .name(organizerName)
                .username(organizerUsername)
                .password(testPassword)
                .confirmPassword(testPassword)
                .passwordKey(testPasswordKey)
                .uic(organizerUIC)
                .fee(organizerFee)
                .mol(testMol)
                .molPhone(testMolPhone)
                .build();
        RegisterOrganizatorResult result = registerOrganizatorCore.process(input);
        assertAll("Verifying registered organizator properties",
                () -> assertEquals(result.getStr(), "Event organizator saves successfully"));
    }

    @Test
    void registerOwnerSuccesful() {
        when(eventOwnerRepository.findEventOwnerByUsername(any())).thenReturn(Optional.empty());
        RegisterOwnerInput input = RegisterOwnerInput.builder()
                .name(ownerName)
                .username(ownerUsername)
                .password(testPassword)
                .confirmPassword(testPassword)
                .passwordKey(testPasswordKey)
               .build();
        RegisterOwnerResult result = registerOwnerCore.process(input);
        assertAll("Verifying registered owner properties",
                () -> assertEquals(result.getStr(), "Event owner saves successfully"));
    }
    @Test
    void registerSellerSuccesful() {
        when(eventSellerRepository.findEventSellerByUsername(any())).thenReturn(Optional.empty());
        RegisterSellerInput input = RegisterSellerInput.builder()
                .name(sellerName)
                .username(sellerUsername)
                .password(testPassword)
                .confirmPassword(testPassword)
                .passwordKey(testPasswordKey)
                .uic(sellerrUIC)
                .fee(sellererFee)
                .mol(testMol)
                .molPhone(testMolPhone)
                .build();
        RegisterSellerResult result = registerSellerCore.process(input);
        assertAll("Verifying registered seller properties",
                () -> assertEquals(result.getStr(), "Event seller saves successfully"));
    }
}