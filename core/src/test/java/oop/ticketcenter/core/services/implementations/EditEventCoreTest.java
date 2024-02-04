package oop.ticketcenter.core.services.implementations;

import oop.ticketcenter.core.interfaces.events.create.SeatTypes;
import oop.ticketcenter.core.interfaces.events.edit.EditEvent;
import oop.ticketcenter.core.interfaces.events.edit.EditEventInput;
import oop.ticketcenter.core.interfaces.events.edit.EditEventResult;
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

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class EditEventCoreTest {
    private final String ownerUsername = "OwnerNo1";
    private final String organizatorUsername = "OrganizatorNo1";
    private final String seller1Username = "SellerNo1";
    private final String seller2Username = "SellerNo2";
    private final String seat = "1st row";
    private final String genre = "Blues";
    private final String type = "concert";
    private final String place = "NDK";
    private final String oldTitle = "Title Old";
    private final String newTitle = "Title New";
    private final Integer maxTickets = 2;
    private final String placeSeatType = "HDK ro 1";

    @Mock
    private EventSellerRepository eventSellerRepository;

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EditEventCore editEvent;

    Event event;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        EventOwner eventOwner = EventOwner.builder()
                .name("Test Owner 1")
                .role(Roles.OWNER)
                .password("ownerpassword1")
                .username(ownerUsername)
                .build();
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
        EventPlace eventPlace = EventPlace.builder()
                .name(place)
                .build();
        EventGenre eventGenre = EventGenre.builder()
                .name(genre)
                .build();
        EventType eventType = EventType.builder()
                .name(type)
                .build();
        SeatType seatType = SeatType.builder()
                .type(seat)
                .build();
        PlaceSeatType placeSeatType = PlaceSeatType.builder()
                .eventPlace(eventPlace)
                .seatType(seatType)
                .quantity(30)
                .build();
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
        event = Event.builder()
                .eventPlace(eventPlace)
                .date(Timestamp.valueOf(LocalDateTime.of(2024, 2, 12, 21, 30)))
                .isArchived(false)
                .eventGenre(eventGenre)
                .eventOrganizator(eventOrganizator)
                .eventOwner(eventOwner)
                .eventType(eventType)
                .maxTicketsPerPerson(maxTickets)
                .soldTickets(0)
                .title(oldTitle)
                .build();
        eventRepository.save(event);
    }

    @AfterEach
    void tearDown() {
        eventRepository.deleteAll();
        eventSellerRepository.deleteAll();
    }

    @Test
    void processSuccesful() {
        List<Event> eventsForSeller = new ArrayList<>();
        when(eventSellerRepository.findEventSellerByUsername(any())).thenReturn(Optional.ofNullable(EventSeller.builder().username(seller1Username).events(eventsForSeller).build()));
        when(eventRepository.findEventByTitle(any())).thenReturn(Optional.ofNullable(event));
        Set<SeatTypes> seatTypesSet = new HashSet<>();
        seatTypesSet.add(new SeatTypes(seat, 20.7));
        EditEventInput input = EditEventInput.builder()
                .oldTitle(oldTitle)
                .newTitle(newTitle)
                .maxTicketsPerPerson(maxTickets)
                .ownerUsername(ownerUsername)
                .build();
        EditEventResult result = editEvent.process(input);
        assertAll("Verifying edited event properties",
                () -> assertEquals(result.getTitle(), newTitle),
                () -> assertEquals(result.getMaxTicketsPerPerson(), maxTickets));
    }
}