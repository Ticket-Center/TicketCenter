package oop.ticketcenter.core.interfaces.users.changePassword;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordResult implements ProcessorResult {
    private boolean successful;

}
