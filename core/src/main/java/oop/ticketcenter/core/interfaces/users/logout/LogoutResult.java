package oop.ticketcenter.core.interfaces.users.logout;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogoutResult implements ProcessorResult {
    private boolean isSuccessful;
}
