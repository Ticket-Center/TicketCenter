package oop.ticketcenter.core.interfaces.events.edit;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorInput;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditEventInput implements ProcessorInput {
    private String username;
    private String password;
}
