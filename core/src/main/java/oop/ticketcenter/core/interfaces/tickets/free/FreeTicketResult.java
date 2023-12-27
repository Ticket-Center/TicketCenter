package oop.ticketcenter.core.interfaces.tickets.free;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FreeTicketResult implements ProcessorResult {
 private Boolean isSuccessfull;
}
