package oop.ticketcenter.core.interfaces.administration.organizators.register;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorInput;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterOrganizatorInput implements ProcessorInput {
    private String name;
    private String username;
    private String password;
    private String confirmPassword;
    private String passwordKey;
    private String molPhone;
    private String mol;
    private String uic;
    private Double fee;
}
