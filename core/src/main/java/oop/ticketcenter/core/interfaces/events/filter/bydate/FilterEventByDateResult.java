package oop.ticketcenter.core.interfaces.events.filter.bydate;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;
import oop.ticketcenter.persistence.entities.Event;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilterEventByDateResult implements ProcessorResult {
    private Set<Event> events;
}
