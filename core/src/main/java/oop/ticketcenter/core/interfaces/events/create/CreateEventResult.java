package oop.ticketcenter.core.interfaces.events.create;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;
import oop.ticketcenter.persistence.entities.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventResult implements ProcessorResult {
    private UUID id;

    private Integer maxTicketsPerPerson;

    private Integer soldTickets;

    private String title;

    private String eventGenre;

    private String eventType;

    private String eventOwnerUsername;

    private String eventOrganizatorUsername;

    private String eventPlace;
}
