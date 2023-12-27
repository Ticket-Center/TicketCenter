package oop.ticketcenter.core.interfaces.events.edit;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorInput;
import oop.ticketcenter.core.interfaces.events.create.SeatTypes;

import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditEventInput implements ProcessorInput {
    private String oldTitle;
    private String ownerUsername;
    private Integer maxTicketsPerPerson;
    private String newTitle;
}
