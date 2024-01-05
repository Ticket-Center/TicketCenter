package oop.ticketcenter.core;

import javafx.application.Application;
import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.interfaces.events.create.CreateEventInput;
import oop.ticketcenter.core.interfaces.events.create.CreateEventResult;
import oop.ticketcenter.core.interfaces.events.create.SeatTypes;
import oop.ticketcenter.core.services.implementations.CreateEventCore;
import oop.ticketcenter.persistence.entities.*;
import oop.ticketcenter.persistence.enums.Rating;
import oop.ticketcenter.persistence.enums.Roles;
import oop.ticketcenter.persistence.repositories.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SpringApplication.class)
@RequiredArgsConstructor
class CreateEventCoreTest {

    private CreateEventCore createEventCore;

    //@Autowired
    private final EventOwnerRepository eventOwnerRepository;

    //@Autowired
    private final EventOrganizatorRepository eventOrganizatorRepository;

    //@Autowired
    private final EventPlaceRepository eventPlaceRepository;

    //@Autowired
    private final EventGenreRepository eventGenreRepository;

   // @Autowired
    private final EventTypeRepository eventTypeRepository;

   // @Autowired
    private final SeatTypeRepository seatTypeRepository;

   // @Autowired
    private final PlaceSeatTypeRepository placeSeatTypeRepository;

   // @Autowired
    private final EventSellerRepository eventSellerRepository;

    private final String ownerUsername = "OwnerNo1";
    private final String organizatorUsername = "OrganizatorNo1";
    private final String seller1Username = "SellerNo1";
    private final String seller2Username = "SellerNo2";
    private final String seat = "1st row";

    private final String genre = "Blues";
    private final String type = "concert";
    private final String place = "NDK";
    private final String title = "OwnerNo1";
    private final Integer maxTickets = 2;

    @BeforeEach
    void setUp() {
        EventOwner eventOwner = EventOwner.builder()
                .name("Test Owner 1")
                .role(Roles.OWNER)
                .password("ownerpassword1")
                .username(ownerUsername)
                .build();
        eventOwnerRepository.save(eventOwner);
        EventOrganizator eventOrganizator = EventOrganizator.builder()
                .name("Test Organizator 1")
                .fee(25.9)
                .username(organizatorUsername)
                .role(Roles.ORGANIZER)
                .password("organizatorpassword1")
                .mol("Iwan Ivanov")
                .uic("12321412")
                .molPhone("0882919191")
                .build();
        eventOrganizatorRepository.save(eventOrganizator);
        EventPlace eventPlace = EventPlace.builder()
                .name(place)
                .build();
        eventPlaceRepository.save(eventPlace);
        EventGenre eventGenre = EventGenre.builder()
                .name(genre)
                .build();
        eventGenreRepository.save(eventGenre);
        EventType eventType = EventType.builder()
                .name(type)
                .build();
        eventTypeRepository.save(eventType);
        SeatType seatType = SeatType.builder()
                .type(seat)
                .build();
        seatTypeRepository.save(seatType);
        PlaceSeatType placeSeatType = PlaceSeatType.builder()
                .eventPlace(eventPlace)
                .seatType(seatType)
                .quantity(30)
                .build();
        placeSeatTypeRepository.save(placeSeatType);
        EventSeller seller1 = EventSeller.builder()
                .username(seller1Username)
                .name("Event Seller 1")
                .password("eventsellerpassword1")
                .role(Roles.SELLER)
                .uic("23232323")
                .mol("Ivan Ivanow")
                .molPhone("0989343434")
                .rating(Rating.FIVE_STARS)
                .fee(23.40)
                .build();
        eventSellerRepository.save(seller1);
        EventSeller seller2 = EventSeller.builder()
                .username(seller2Username)
                .name("Event Seller 2")
                .password("eventsellerpassword2")
                .role(Roles.SELLER)
                .uic("89232323")
                .mol("Petar Ivanow")
                .molPhone("0989378434")
                .rating(Rating.THREE_STARS)
                .fee(23.40)
                .build();
        eventSellerRepository.save(seller2);
    }

    @AfterEach
    void tearDown() {
        eventOwnerRepository.deleteAll();
        eventOrganizatorRepository.deleteAll();
        eventPlaceRepository.deleteAll();
        eventGenreRepository.deleteAll();
        eventTypeRepository.deleteAll();
        seatTypeRepository.deleteAll();
        placeSeatTypeRepository.deleteAll();
        eventSellerRepository.deleteAll();
    }

    @Test
    void processSuccesful() {
        Set<SeatTypes> seatTypesSet = new HashSet<>();
        seatTypesSet.add(new SeatTypes(seat, 20.7));
        CreateEventInput input = CreateEventInput.builder()
                .maxTicketsPerPerson(maxTickets)
                .title(title)
                .eventGenre(genre)
                .eventType(type)
                .eventOwnerUsername(ownerUsername)
                .eventOrganizatorUsername(organizatorUsername)
                .eventPlace(place)
                .eventSellers(List.of(seller1Username, seller2Username))
                .seatTypes(seatTypesSet)
                .build();
        CreateEventResult result = createEventCore.process(input);
        assertNotNull(result.getId());
    }
}