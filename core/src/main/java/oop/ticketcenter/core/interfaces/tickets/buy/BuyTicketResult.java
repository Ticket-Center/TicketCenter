package oop.ticketcenter.core.interfaces.tickets.buy;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuyTicketResult implements ProcessorResult {
    private UUID ticketsId;
}
