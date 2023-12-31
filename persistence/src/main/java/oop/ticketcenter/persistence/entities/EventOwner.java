package oop.ticketcenter.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import oop.ticketcenter.persistence.enums.Roles;

import java.util.UUID;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EventOwner")
public class EventOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Length(max=60, message = "Name should be 60 characters")
    private String name;

    @Enumerated(value = EnumType.STRING)
    private Roles role = Roles.OWNER;

    @Length(min = 8, max = 255, message = "Password should be between 8 and 255 characters")
    private String password;

    @Length(max = 20, message = "Username must be less than {max} symbols")
    private String username;

    private String passwordKey;

}
