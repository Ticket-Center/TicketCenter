package oop.ticketcenter.core.interfaces.login;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResult implements ProcessorResult {
    private UUID userId;
}
