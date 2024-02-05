package oop.ticketcenter.core.interfaces.users.edit;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorInput;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditProfileInput implements ProcessorInput {
    private String firstName;
    private String lastName;
    private String passwordKey;
    private String address;
    private String phone;
}
