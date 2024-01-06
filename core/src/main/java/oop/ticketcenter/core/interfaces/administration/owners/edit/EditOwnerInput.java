package oop.ticketcenter.core.interfaces.administration.owners.edit;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorInput;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditOwnerInput implements ProcessorInput {
    private String name;
    private String username;
}
