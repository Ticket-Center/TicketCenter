package oop.ticketcenter.core.services.implementations;

import oop.ticketcenter.core.interfaces.events.create.CreateEventInput;
import oop.ticketcenter.core.interfaces.events.create.CreateEventResult;
import oop.ticketcenter.core.interfaces.events.create.SeatTypes;
import oop.ticketcenter.persistence.entities.*;
import oop.ticketcenter.persistence.enums.Rating;
import oop.ticketcenter.persistence.enums.Roles;
import oop.ticketcenter.persistence.repositories.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreateEventCoreTest {
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
    private final String placeSeatType = "HDK ro 1";
    @Mock
    private EventOwnerRepository eventOwnerRepository;
    @Mock
    private EventOrganizatorRepository eventOrganizatorRepository;
    @Mock
    private EventPlaceRepository eventPlaceRepository;
    @Mock
    private EventGenreRepository eventGenreRepository;
    @Mock
    private EventTypeRepository eventTypeRepository;
    @Mock
    private SeatTypeRepository seatTypeRepository;
    @Mock
    private PlaceSeatTypeRepository placeSeatTypeRepository;
    @Mock
    private EventSellerRepository eventSellerRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private SoldTicketsRepository soldTicketsRepository;

    @Mock
    private EventSeatPriceRepository eventSeatPriceRepository;
    @InjectMocks
    private CreateEventCore createEventCore;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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
        List<Event> eventsForSeller = new ArrayList<>();
        when(eventOwnerRepository.findEventOwnerByUsername(any())).thenReturn(Optional.ofNullable(EventOwner.builder().username(ownerUsername).build()));
        when(eventOrganizatorRepository.findEventOrganizatorByUsername(any())).thenReturn(Optional.ofNullable(EventOrganizator.builder().username(organizatorUsername).build()));
        when(eventPlaceRepository.findEventPlaceByName(any())).thenReturn(Optional.ofNullable(EventPlace.builder().name(place).build()));
        when(eventGenreRepository.findEventGenreByName(any())).thenReturn(Optional.ofNullable(EventGenre.builder().name(genre).build()));
        when(eventTypeRepository.findEventTypeByName(any())).thenReturn(Optional.ofNullable(EventType.builder().name(type).build()));
        when(seatTypeRepository.findSeatTypeByType(any())).thenReturn(Optional.ofNullable(SeatType.builder().type(seat).build()));
        when(eventSellerRepository.findEventSellerByUsername(any())).thenReturn(Optional.ofNullable(EventSeller.builder().username(seller1Username).events(eventsForSeller).build()));
        when(placeSeatTypeRepository.findPlaceSeatTypeByEventPlaceAndSeatType(any(), any())).thenReturn(Optional.ofNullable(PlaceSeatType.builder().eventPlace(EventPlace.builder().name(place).build()).seatType(SeatType.builder().type(seat).build()).build()));
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
                .eventSellers(List.of(seller1Username))
                .seatTypes(seatTypesSet)
                .dateOfEvent(LocalDateTime.of(2024, 02, 12, 21, 30))
                .build();
        CreateEventResult result = createEventCore.process(input);
        assertNotNull(result);
    }
}