package oop.ticketcenter.core.services.helpers;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.persistence.entities.EventSeatPrice;
import oop.ticketcenter.persistence.entities.Ticket;
import oop.ticketcenter.persistence.repositories.EventSeatPriceRepository;
import oop.ticketcenter.persistence.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GetTickets {
    private final TicketRepository ticketRepository;
    private final EventSeatPriceRepository eventSeatPriceRepository;

    private final Set<Ticket> tickets=new HashSet<>();

    public Set<Ticket> fetchAllTickets(){
        tickets.clear();
        tickets.addAll(new HashSet<>(ticketRepository.findAll()));
        setTicketsSeatTypeName(tickets);
        setTicketsPlaceName(tickets);
        setTicketsEventName(tickets);
        setTicketsDate(tickets);
        setTicketsPrice(tickets);
        return tickets;
    }

    private void setTicketsSeatTypeName(Set<Ticket> tickets){
        for(Ticket ticket : tickets){
            if(ticket.getEventSeatPrice() != null && ticket.getEventSeatPrice().getPlaceSeatType() != null){
                eventSeatPriceRepository.findById(ticket.getEventSeatPrice().getId())
                        .map(eventSeatPrice -> eventSeatPrice.getPlaceSeatType().getSeatType())
                        .ifPresent(seatTypeName -> ticket.getEventSeatPrice().getPlaceSeatType().setSeatType(seatTypeName));

            }
        }
    }

    private void setTicketsPlaceName(Set<Ticket> tickets){
        for (Ticket ticket : tickets){
            if(ticket.getEventSeatPrice() != null && ticket.getEventSeatPrice().getPlaceSeatType() != null){
                eventSeatPriceRepository.findById(ticket.getEventSeatPrice().getId())
                        .map(eventSeatPrice -> eventSeatPrice.getPlaceSeatType().getEventPlace().getName())
                        .ifPresent(placeName -> ticket.getEventSeatPrice().getPlaceSeatType().getEventPlace().setName(placeName));
            }
        }
    }

    private void setTicketsEventName(Set<Ticket> tickets){
        for(Ticket ticket: tickets){
            if(ticket.getEventSeatPrice()!=null){
                eventSeatPriceRepository.findById(ticket.getEventSeatPrice().getId())
                        .map(eventSeatPrice -> eventSeatPrice.getEvent().getTitle())
                        .ifPresent(eventTitle -> ticket.getEventSeatPrice().getEvent().setTitle(eventTitle));
                }
            }
        }
    private void setTicketsDate(Set<Ticket> tickets) {
        for(Ticket ticket:tickets){
            if(ticket.getEventSeatPrice()!=null){
                eventSeatPriceRepository.findById(ticket.getEventSeatPrice().getId())
                        .map(eventSeatPrice -> eventSeatPrice.getEvent().getDate())
                        .ifPresent(eventDate->ticket.getEventSeatPrice().getEvent().setDate(eventDate));
            }
        }
    }

    private void setTicketsPrice(Set<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            EventSeatPrice eventSeatPrice = ticket.getEventSeatPrice();
            if (eventSeatPrice != null) {
                eventSeatPriceRepository.findById(eventSeatPrice.getId())
                        .map(EventSeatPrice::getPrice)
                        .ifPresent(eventSeatPrice::setPrice);
            }
        }
    }
}

