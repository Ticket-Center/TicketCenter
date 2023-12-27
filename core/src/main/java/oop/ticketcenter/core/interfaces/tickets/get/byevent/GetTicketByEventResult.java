package oop.ticketcenter.core.interfaces.tickets.get.byevent;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;
import oop.ticketcenter.persistence.entities.Event;
import oop.ticketcenter.persistence.entities.Ticket;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTicketByEventResult implements ProcessorResult {
 private List<TicketByEvent> ticketsByEvents;
}
