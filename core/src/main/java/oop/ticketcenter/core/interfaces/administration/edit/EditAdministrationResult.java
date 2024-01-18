package oop.ticketcenter.core.interfaces.administration.edit;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditAdministrationResult implements ProcessorResult {
    private String str;
}
