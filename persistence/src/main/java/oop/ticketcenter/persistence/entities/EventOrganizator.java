package oop.ticketcenter.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import oop.ticketcenter.persistence.enums.Roles;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EventOrganizators")
public class EventOrganizator {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Length(max = 80, message = "Name should be max 80 characters")
    private String name;

    @Length(min = 8, max = 255, message = "Password should be between 8 and 255 characters")
    private String password;

    @Length(max = 20, message = "Username must be less than {max} symbols")
    private String username;

    @Length(min = 10, max = 10, message = "Uic should be 10 characters")
    private String uic;

    //@Length(max = 10, message = "Mol should be 10 characters")
    @Size(min = 6, message = "Mol should be 10 characters")
    private String mol;

    private Double fee;

    private String passwordKey;

    @Length(min = 10, message = "Mol phone should be 10 characters")
    private String molPhone;

    @Enumerated(value = EnumType.STRING)
    private Roles role = Roles.ORGANIZER;
}
