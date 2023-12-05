package oop.ticketcenter.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import oop.ticketcenter.persistence.enums.Roles;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String username;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Roles role = Roles.ADMIN;
}
