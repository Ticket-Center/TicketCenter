package oop.ticketcenter.core.interfaces.tickets.free;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorInput;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FreeTicketInput implements ProcessorInput {
    private String username;
    private String password;
}
