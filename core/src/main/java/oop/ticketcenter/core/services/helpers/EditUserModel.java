package oop.ticketcenter.core.services.helpers;

import lombok.*;
import oop.ticketcenter.persistence.entities.Client;
import oop.ticketcenter.persistence.entities.EventOrganizator;
import oop.ticketcenter.persistence.entities.EventOwner;
import oop.ticketcenter.persistence.entities.EventSeller;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditUserModel {
    private String name;
    private String username;
    private String molPhone;
    private String mol;
    private Double fee;
    private String role;
}
