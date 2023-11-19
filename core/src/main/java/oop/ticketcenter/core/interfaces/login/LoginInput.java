package oop.ticketcenter.core.interfaces.login;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorInput;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginInput implements ProcessorInput {
    private String username;
    private String password;
}
