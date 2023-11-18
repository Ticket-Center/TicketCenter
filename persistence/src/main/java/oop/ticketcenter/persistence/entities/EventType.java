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
@Table(name = "EventTypes")
public class EventType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Length(min = 16, max = 16, message = "Invalid id length")
    private UUID id;

    @Length(max=60, message = "Type should be max 60 characters")
    private String name;
}
