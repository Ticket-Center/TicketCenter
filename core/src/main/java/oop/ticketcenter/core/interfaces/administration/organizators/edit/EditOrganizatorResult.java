package oop.ticketcenter.core.interfaces.administration.organizators.edit;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditOrganizatorResult implements ProcessorResult {
    private String str;
}
