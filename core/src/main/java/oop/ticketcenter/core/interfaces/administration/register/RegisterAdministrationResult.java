package oop.ticketcenter.core.interfaces.administration.register;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAdministrationResult implements ProcessorResult {
    private String str;
}
