package oop.ticketcenter.core.interfaces.users.changePassword;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorInput;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordInput implements ProcessorInput {
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;
}
