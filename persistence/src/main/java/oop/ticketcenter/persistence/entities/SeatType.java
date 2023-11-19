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
@Table(name = "SeatTypes")
public class SeatType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Length(max=60, message = "Type should be max 60 characters")
    private String type;

    private Integer quantity;

    @ManyToOne
    private EventPlace eventPlace;
}
