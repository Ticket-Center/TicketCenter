package oop.ticketcenter.core.interfaces.administration.register;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorInput;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterAdministrationInput implements ProcessorInput {
    private String name;
    private String username;
    private String password;
    private String confirmPassword;
    private String role;
    private String passwordKey;
    private String molPhone;
    private String mol;
    private String uic;
    private Double fee;
}
