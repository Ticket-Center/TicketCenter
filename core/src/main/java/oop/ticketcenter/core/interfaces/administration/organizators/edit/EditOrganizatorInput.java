package oop.ticketcenter.core.interfaces.administration.organizators.edit;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorInput;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditOrganizatorInput implements ProcessorInput {
    private String name;
    private String username;
    private String molPhone;
    private String mol;
    private Double fee;
}
