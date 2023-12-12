package oop.ticketcenter.core.interfaces.events.create;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorInput;
import oop.ticketcenter.persistence.entities.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventInput implements ProcessorInput {
    private Integer maxTicketsPerPerson;
    private Integer soldTickets;
    private String title;
    private String eventGenre;
    private String eventType;
    private String eventOwnerUsername;
    private String eventOrganizatorUsername;
    private String eventPlace;
}
