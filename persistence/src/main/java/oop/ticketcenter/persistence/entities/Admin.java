package oop.ticketcenter.persistence.entities;

import jakarta.persistence.*;
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
@Table(name="Admins")
public class Admin {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @Length(max=60, message = "First name should be max 60 characters")
    private String firstname;

    @Length(max=60, message = "Last name should be max 60 characters")
    private String lastname;

    @Length(max=60, message = "Username should be max 60 characters")
    private String username;

    @Length(min = 8, max = 255, message = "Password should be between 8 and 255 characters")
    private String password;

    @Enumerated(value=EnumType.STRING)
    private Roles role= Roles.ADMIN;
}
