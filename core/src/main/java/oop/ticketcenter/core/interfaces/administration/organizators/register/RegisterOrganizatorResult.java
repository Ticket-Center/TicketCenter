package oop.ticketcenter.core.interfaces.administration.organizators.register;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterOrganizatorResult implements ProcessorResult {
    private String str;
}
