package oop.ticketcenter.core.interfaces.events.edit;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditEventResult implements ProcessorResult {
    private UUID userId;
}
