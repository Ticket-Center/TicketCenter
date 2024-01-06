package oop.ticketcenter.core.interfaces.administration.owners.register;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterOwnerResult implements ProcessorResult {
    private String str;
}
