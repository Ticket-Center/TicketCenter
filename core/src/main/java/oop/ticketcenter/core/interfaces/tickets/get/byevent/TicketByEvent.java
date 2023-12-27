package oop.ticketcenter.core.interfaces.tickets.get.byevent;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketByEvent {
    private Integer soldTickets;
    private Integer maxTickets;
}
