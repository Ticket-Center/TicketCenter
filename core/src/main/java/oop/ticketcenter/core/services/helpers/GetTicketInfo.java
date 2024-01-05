package oop.ticketcenter.core.services.helpers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import oop.ticketcenter.persistence.entities.EventSeatPrice;
import oop.ticketcenter.persistence.entities.SeatType;
import oop.ticketcenter.persistence.repositories.EventSeatPriceRepository;
import oop.ticketcenter.persistence.repositories.PlaceSeatTypeRepository;
import oop.ticketcenter.persistence.repositories.SeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GetTicketInfo {
    private final EventSeatPriceRepository eventSeatPriceRepository;
    private final PlaceSeatTypeRepository placeSeatTypeRepository;
    private final SeatTypeRepository seatTypeRepository;

    @Getter
    private final Set<EventSeatPrice> eventTickets=new HashSet<>();

    public Set<EventSeatPrice> fetchAllEventSeatPrice(){
        eventTickets.clear();
        eventTickets.addAll(eventSeatPriceRepository.findAll());
        setSeatTypes();
        return eventTickets;
    }
    private void setSeatTypes() {
        for (EventSeatPrice event : eventTickets) {
            if(event.getPlaceSeatType() != null) {
                String name = placeSeatTypeRepository.findById(event.getPlaceSeatType().getId())
                        .map(placeSeatType -> seatTypeRepository.findById(placeSeatType.getSeatType().getId()))
                        .flatMap(seatTypeOpt -> seatTypeOpt.map(SeatType::getType))
                        .orElse(null);
                event.getPlaceSeatType().getSeatType().setType(name);
            }
        }
    }

}