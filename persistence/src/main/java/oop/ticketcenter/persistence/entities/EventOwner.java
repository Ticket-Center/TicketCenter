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
@Table(name = "EventOwner")
public class EventOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Length(min = 16, max = 16, message = "Invalid id length")
    private UUID id;

    @Length(max=60, message = "Name should be 60 characters")
    private String name;
}
