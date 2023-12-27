package oop.ticketcenter.core.interfaces.events.edit;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;
import oop.ticketcenter.core.interfaces.events.create.SeatTypes;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditEventResult implements ProcessorResult {
    private Integer maxTicketsPerPerson;
    private Integer soldTickets;
    private String title;
    private String eventGenre;
    private String eventType;
    private String eventOwnerUsername;
    private String eventOrganizatorUsername;
    private String eventPlace;
    private List<String> eventSellers;
}
