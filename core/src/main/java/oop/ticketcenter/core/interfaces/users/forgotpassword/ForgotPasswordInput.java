package oop.ticketcenter.core.interfaces.users.forgotpassword;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorInput;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPasswordInput implements ProcessorInput {
    private String username;
    private String passwordkey;
}
