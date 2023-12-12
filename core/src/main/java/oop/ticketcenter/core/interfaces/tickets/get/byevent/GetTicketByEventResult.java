package oop.ticketcenter.core.interfaces.tickets.get.byevent;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTicketByEventResult implements ProcessorResult {
    private UUID userId;
}
