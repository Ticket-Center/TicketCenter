package oop.ticketcenter.core.interfaces.events.delete;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteEventResult implements ProcessorResult {
    private boolean sucessfull;
}
