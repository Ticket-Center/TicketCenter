package oop.ticketcenter.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
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

    private String name;

    @Length(max = 20, message = "Username must be less than {max} symbols")
    private String username;

    private String uic;

    private String mol;

    private Double fee;
    @Length(min = 10, max = 10, message = "Invalid molPhone")
    private String molPhone;
}
