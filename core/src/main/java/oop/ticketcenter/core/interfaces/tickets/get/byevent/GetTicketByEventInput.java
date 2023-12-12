package oop.ticketcenter.core.interfaces.tickets.get.byevent;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorInput;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetTicketByEventInput implements ProcessorInput {
    private String username;
    private String password;
}
