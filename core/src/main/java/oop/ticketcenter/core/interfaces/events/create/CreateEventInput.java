package oop.ticketcenter.core.interfaces.events.create;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorInput;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventInput implements ProcessorInput {
    private String username;
    private String password;
}
