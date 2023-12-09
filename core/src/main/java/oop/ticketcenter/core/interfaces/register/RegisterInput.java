package oop.ticketcenter.core.interfaces.register;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorInput;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterInput implements ProcessorInput {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String confirmPassword;
    private String phone;
    private String address;
    private String passwordKey;
}
