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
    @Length(min = 16, max = 16, message = "Invalid id length")
    private UUID id;

    @Length(max=80, message = "Name should be max 80 characters")
    private String name;

    @Length(min=10,max=10, message = "Uic should be 10 characters")
    private String uic;

    @Length(min=10,max=10, message = "Mol should be 10 characters")
    private String mol;

    private Double fee;

    @Length(min = 10, max = 10, message = "Mol phone should be 10 characters")
    private String molPhone;
}
