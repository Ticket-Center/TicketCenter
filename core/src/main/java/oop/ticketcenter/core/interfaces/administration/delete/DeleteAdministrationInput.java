package oop.ticketcenter.core.interfaces.administration.delete;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorInput;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteAdministrationInput implements ProcessorInput {
    private String username;
    private String role;
}
