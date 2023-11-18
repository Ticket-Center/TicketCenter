package oop.ticketcenter.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.UUID;

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
    @Length(min = 16, max = 16, message = "Invalid id length")
    private UUID id;

    @Length(max=60, message = "First name should be max 60 characters")
    private String firstname;

    @Length(max=60, message = "Last name should be max 60 characters")
    private String lastname;

    @Length(max=100, message ="Address should be max 100 characters")
    private String address;

    @Length(min=10,max=10,message = "Phone number should be 10 characters")
    private String phone;

    @ManyToMany
    @JoinTable(
            name = "Client_Tickets",
            joinColumns = @JoinColumn(name = "Clients_id", referencedColumnName = "id"),
            inverseJoinColumns =  @JoinColumn(name = "Tickets_id", referencedColumnName = "id")
    )
    private List<Ticket> tickets;
}
