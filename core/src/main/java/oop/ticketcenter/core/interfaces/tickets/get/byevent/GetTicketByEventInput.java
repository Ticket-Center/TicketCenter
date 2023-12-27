package oop.ticketcenter.core.interfaces.tickets.get.byevent;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorInput;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetTicketByEventInput implements ProcessorInput {
    private List<String> eventTitles;
}