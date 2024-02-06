package oop.ticketcenter.ui.helpers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import oop.ticketcenter.persistence.entities.Event;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TicketData {
    @Getter
    @Setter
    private Event event;

    /*@Getter
    @Setter
    private Set<EventSeatPrice> ticketsInfo;*/
    @Getter
    @Setter
    private String seatType;
    @Getter
    @Setter
    private Double price;


}
