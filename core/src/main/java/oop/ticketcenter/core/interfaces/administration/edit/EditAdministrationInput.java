package oop.ticketcenter.core.interfaces.administration.edit;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorInput;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditAdministrationInput implements ProcessorInput {
    private String name;
    private String username;
    private String molPhone;
    private String mol;
    private Double fee;
    private String role;
}
