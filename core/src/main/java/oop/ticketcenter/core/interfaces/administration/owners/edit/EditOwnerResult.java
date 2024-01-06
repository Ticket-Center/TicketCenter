package oop.ticketcenter.core.interfaces.administration.owners.edit;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditOwnerResult implements ProcessorResult {
    private String str;
}
