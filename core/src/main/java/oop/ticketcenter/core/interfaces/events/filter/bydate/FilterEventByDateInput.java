package oop.ticketcenter.core.interfaces.events.filter.bydate;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorInput;
import oop.ticketcenter.core.interfaces.events.create.SeatTypes;
import oop.ticketcenter.persistence.entities.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilterEventByDateInput implements ProcessorInput {
  private LocalDate startDate;
  private LocalDate endDate;
  private Set<Event> events;
}
