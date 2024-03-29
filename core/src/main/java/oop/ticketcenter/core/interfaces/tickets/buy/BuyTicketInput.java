package oop.ticketcenter.core.interfaces.tickets.buy;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorInput;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuyTicketInput implements ProcessorInput {
    private String eventTitle;
    //private String eventSellerUsername;
    private Integer numberTickets;
    private String ticketType;
}
