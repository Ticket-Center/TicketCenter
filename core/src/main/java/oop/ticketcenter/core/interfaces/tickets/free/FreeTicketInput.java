package oop.ticketcenter.core.interfaces.tickets.free;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorInput;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FreeTicketInput implements ProcessorInput {
    private UUID ticketId;
}
