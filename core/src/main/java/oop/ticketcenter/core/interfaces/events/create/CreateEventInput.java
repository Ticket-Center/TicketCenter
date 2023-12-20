package oop.ticketcenter.core.interfaces.events.create;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorInput;
import oop.ticketcenter.persistence.entities.*;

import java.util.List;
import java.util.Map;
import java.util.Set;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventInput implements ProcessorInput {
    private Integer maxTicketsPerPerson;
    private String title;
    private String eventGenre;
    private String eventType;
    private String eventOwnerUsername;
    private String eventOrganizatorUsername;
    private String eventPlace;
    private List<String> eventSellers;
    private Set<SeatTypes> seatTypes;
}
