package oop.ticketcenter.core.interfaces.tickets.get.byowner;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTicketByOwnerResult implements ProcessorResult {
    private UUID userId;
}
