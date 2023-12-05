package oop.ticketcenter.core.interfaces.events.delete;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteEventResult implements ProcessorResult {
    private UUID userId;
}
