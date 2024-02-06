package oop.ticketcenter.ui.helpers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import oop.ticketcenter.persistence.entities.Ticket;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SoldTicketData {
    @Getter
    @Setter
    private Ticket ticket;
}
