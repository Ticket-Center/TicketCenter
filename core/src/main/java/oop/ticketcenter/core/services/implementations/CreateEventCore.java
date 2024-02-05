package oop.ticketcenter.core.services.implementations;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.exceptions.IncorrectInputException;
import oop.ticketcenter.core.exceptions.UserNotFoundException;
import oop.ticketcenter.core.interfaces.events.create.CreateEvent;
import oop.ticketcenter.core.interfaces.events.create.CreateEventInput;
import oop.ticketcenter.core.interfaces.events.create.CreateEventResult;
import oop.ticketcenter.persistence.entities.*;
import oop.ticketcenter.persistence.entities.EventSeatPrice;
import oop.ticketcenter.persistence.repositories.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CreateEventCore implements CreateEvent {
    private final EventRepository eventRepository;
    private final EventOrganizatorRepository eventOrganizatorRepository;
    private final EventOwnerRepository eventOwnerRepository;
    private final EventSellerRepository eventSellerRepository;
    private final EventPlaceRepository eventPlaceRepository;
    private final EventGenreRepository eventGenreRepository;
    private final EventTypeRepository eventTypeRepository;
    private final PlaceSeatTypeRepository placeSeatTypeRepository;
    private final SeatTypeRepository seatTypeRepository;
    private final EventSeatPriceRepository eventSeatPriceRepository;
    private final SoldTicketsRepository soldTicketsRepository;
    private final NotificationRepository notificationRepository;

    @Override
    public CreateEventResult process(CreateEventInput input) {
        if (input.getEventGenre().isEmpty() || input.getEventPlace().isEmpty() ||
                input.getEventType().isEmpty() || input.getTitle().isEmpty() ||
                input.getEventOwnerUsername().isEmpty() || input.getEventOrganizatorUsername().isEmpty() ||
                input.getMaxTicketsPerPerson() == 0 || input.getTitle().isBlank() ||
                input.getEventSellers().isEmpty() || input.getDateOfEvent().isBefore(LocalDateTime.now())) {
            throw new IncorrectInputException("Not all required fields have values");
        }
        EventOwner owner = eventOwnerRepository.findEventOwnerByUsername(input.getEventOwnerUsername())
                .orElseThrow(() -> new UserNotFoundException("Event owner with this username not found"));

        EventOrganizator organizator = eventOrganizatorRepository.findEventOrganizatorByUsername(input.getEventOrganizatorUsername())
                .orElseThrow(() -> new UserNotFoundException("Event organizator with this username not found"));

        EventPlace place = eventPlaceRepository.findEventPlaceByName(input.getEventPlace())
                .orElseThrow(() -> new UserNotFoundException("Event place with this name not found"));

        EventGenre genre = eventGenreRepository.findEventGenreByName(input.getEventGenre())
                .orElseThrow(() -> new UserNotFoundException("Event genre with this name not found"));

        EventType type = eventTypeRepository.findEventTypeByName(input.getEventType())
                .orElseThrow(() -> new UserNotFoundException("Event type with this name not found"));

        Event event = Event.builder()
                .eventPlace(place)
                .eventOrganizator(organizator)
                .eventGenre(genre)
                .eventType(type)
                .eventOwner(owner)
                .maxTicketsPerPerson(input.getMaxTicketsPerPerson())
                .soldTickets(0)
                .title(input.getTitle())
                .date(Timestamp.valueOf(input.getDateOfEvent()))
                .isArchived(false)
                .build();

        eventRepository.save(event);

        input.getSeatTypes().stream().forEach(seat -> {
            SeatType seatType = seatTypeRepository.findSeatTypeByType(seat.getType())
                    .orElseThrow(() -> new UserNotFoundException("Seat type not found"));
            PlaceSeatType placeSeatType = placeSeatTypeRepository.findPlaceSeatTypeByEventPlaceAndSeatType(place, seatType)
                    .orElseThrow(() -> new UserNotFoundException("Place seat type not found"));
            EventSeatPrice seatPrice = EventSeatPrice.builder()
                    .event(event)
                    .placeSeatType(placeSeatType)
                    .price(seat.getPrice())
                    .build();
            eventSeatPriceRepository.save(seatPrice);
            SoldTickets soldTickets = SoldTickets.builder()
                    .event(event)
                    .seatType(placeSeatType)
                    .quantity(0)
                    .build();
            soldTicketsRepository.save(soldTickets);
        });

        List<UUID> sellersToBeNotified = new ArrayList<>();
        for (String eventSeller : input.getEventSellers()) {
            EventSeller seller = eventSellerRepository.findEventSellerByUsername(eventSeller)
                    .orElseThrow(() -> new UserNotFoundException("Event seller with this username not found"));;
            seller.getEvents().add(event);
            sellersToBeNotified.add(seller.getId());
            eventSellerRepository.save(seller);
        }

        Notification notification = Notification.builder()
                .message("New event is created for you: " + event.getTitle())
                .isReceived(false)
                .receivers(sellersToBeNotified)
                .build();
        notificationRepository.save(notification);
        return CreateEventResult.builder()
                .id(event.getId())
                .build();
    }
}
