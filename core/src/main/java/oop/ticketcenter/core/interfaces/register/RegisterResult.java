package oop.ticketcenter.core.interfaces.register;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResult implements ProcessorResult {
    private String str;
}
