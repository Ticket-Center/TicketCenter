package oop.ticketcenter.core.interfaces.users.forgotpassword;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordResult implements ProcessorResult {
    private String newPassword;
}
