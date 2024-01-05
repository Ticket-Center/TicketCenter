package oop.ticketcenter.core.interfaces.administration.delete;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteAdministrationResult implements ProcessorResult {
    private String str;
}
