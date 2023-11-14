package oop.ticketcenter.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

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
    private UUID id;

    private String firstname;
    private String lastname;
    private String address;
    private String phone;

    @ManyToMany
    @JoinTable(
            name = "Client_Tickets",
            joinColumns = @JoinColumn(name = "Clients_id", referencedColumnName = "id"),
            inverseJoinColumns =  @JoinColumn(name = "Tickets_id", referencedColumnName = "id")
    )
    private List<Ticket> tickets;
}
