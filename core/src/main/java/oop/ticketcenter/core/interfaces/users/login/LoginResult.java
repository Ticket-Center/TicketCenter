package oop.ticketcenter.core.interfaces.users.login;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;
import oop.ticketcenter.persistence.enums.Roles;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResult implements ProcessorResult {
    private UUID userId;
    private Roles roles;
}
