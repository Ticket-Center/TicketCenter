package oop.ticketcenter.core.interfaces.events.delete;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorInput;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteEventInput implements ProcessorInput {
    private String eventTitle;
}
