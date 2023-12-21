package oop.ticketcenter.core.interfaces.tickets.get.byowner;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorInput;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetTicketByOwnerInput implements ProcessorInput {
    private String username;
    private String password;
}
