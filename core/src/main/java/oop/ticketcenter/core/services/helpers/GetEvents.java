package oop.ticketcenter.core.services.helpers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import oop.ticketcenter.persistence.entities.*;
import oop.ticketcenter.persistence.repositories.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetEvents {

    private final EventRepository eventRepository;
    private final EventTypeRepository eventTypeRepository;
    private final EventGenreRepository eventGenreRepository;
    private final EventPlaceRepository eventPlaceRepository;
    private final EventSellerRepository eventSellerRepository;
    private final EventOwnerRepository eventOwnerRepository;
    private final EventOrganizatorRepository eventOrganizatorRepository;

    @Getter
    private final Set<Event> events = new HashSet<>();

    public Set<Event> fetchAllEvents() {
        events.clear();
        events.addAll(eventRepository.findAll().stream()
                .filter(event -> event.getIsArchived().equals(false))
                .collect(Collectors.toSet()));
        setEventsTypeName(events);
        setEventsGenreName(events);
        setEventsPlaceName(events);
        return events;
    }

    public List<String> fetchAllEventsTitles(){
        return new ArrayList<>(eventRepository.findAll().stream()
                .filter(event -> event.getIsArchived().equals(false))
                .map(Event::getTitle)
                .toList());
    }
    public Set<Event> fetchEventsBySeller(String sellerUsername) {
        Optional<EventSeller> eventSellerOpt = eventSellerRepository.findEventSellerByUsername(sellerUsername);

        if (eventSellerOpt.isPresent()) {
            Set<Event> sellerEvents = eventSellerOpt.get().getEvents().stream()
                    .filter(event -> !event.getIsArchived())
                    .collect(Collectors.toSet());

            setEventsTypeName(sellerEvents);
            setEventsGenreName(sellerEvents);
            setEventsPlaceName(sellerEvents);

            return sellerEvents;
        }
        return Collections.emptySet();
    }
    private void setEventsTypeName(Set<Event> events){
        for(Event event: events){
            if(event.getEventType()!=null){
                String name=eventTypeRepository.findById(event.getEventType().getId())
                        .map(EventType::getName)
                        .orElse(null);
                event.getEventType().setName(name);
            }
        }
    }
    private void setEventsGenreName(Set<Event> events){
        for(Event event: events){
            if(event.getEventGenre()!=null){
                String name=eventGenreRepository.findById(event.getEventGenre().getId())
                        .map(EventGenre::getName)
                        .orElse(null);
                event.getEventGenre().setName(name);
            }
        }
    }

    private void setEventsPlaceName(Set<Event> events){
        for(Event event: events){
            if(event.getEventPlace()!=null){
                String name=eventPlaceRepository.findById(event.getEventPlace().getId())
                        .map(EventPlace::getName)
                        .orElse(null);
                event.getEventPlace().setName(name);
            }
        }
    }
    public Set<UUID> fetchEventIdByOwner(String ownerUsername) {
        Optional<EventOwner> eventOwnerOpt = eventOwnerRepository.findEventOwnerByUsername(ownerUsername);

        if (eventOwnerOpt.isPresent()) {
            EventOwner eventOwner = eventOwnerOpt.get();
            return eventRepository.findByEventOwner(eventOwner).stream()
                    .filter(event -> !event.getIsArchived())
                    .map(Event::getId)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

    public Set<UUID> fetchEventIdByOrganizator(String organizatorUsername) {
        Optional<EventOrganizator> eventOrganizatorOpt = eventOrganizatorRepository.findEventOrganizatorByUsername(organizatorUsername);

        if (eventOrganizatorOpt.isPresent()) {
            EventOrganizator eventOrganizator = eventOrganizatorOpt.get();
            return eventRepository.findByEventOrganizator(eventOrganizator).stream()
                    .filter(event -> !event.getIsArchived())
                    .map(Event::getId)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

}
