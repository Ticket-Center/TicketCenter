package oop.ticketcenter.core.interfaces.users.edit;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditProfileResult implements ProcessorResult {
    private boolean successful;
}
