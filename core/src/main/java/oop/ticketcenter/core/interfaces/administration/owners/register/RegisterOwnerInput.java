package oop.ticketcenter.core.interfaces.administration.owners.register;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorInput;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterOwnerInput implements ProcessorInput {
    private String name;
    private String username;
    private String password;
    private String confirmPassword;
    private String passwordKey;
}
