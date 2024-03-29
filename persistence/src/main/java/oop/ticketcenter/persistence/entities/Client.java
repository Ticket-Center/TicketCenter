package oop.ticketcenter.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import oop.ticketcenter.persistence.enums.Roles;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.UUID;

// admin is a new table

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Length(max=60, message = "First name should be max 60 characters")
    private String firstname;

    @Length(max=60, message = "Last name should be max 60 characters")
    private String lastname;

    @Length(max=100, message ="Address should be max 100 characters")
    private String address;

    @Length(max=60, message = "Username should be max 60 characters")
    private String username;

    @Length(min = 8, max = 255, message = "Password should be between 8 and 255 characters")
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Roles role = Roles.CLIENT;

    @Length(min=10,max=10,message = "Phone number should be 10 characters")
    private String phone;

    private String passwordKey;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Client_Tickets",
            joinColumns = @JoinColumn(name = "Clients_id", referencedColumnName = "id"),
            inverseJoinColumns =  @JoinColumn(name = "Tickets_id", referencedColumnName = "id")
    )
    private List<Ticket> tickets;
}