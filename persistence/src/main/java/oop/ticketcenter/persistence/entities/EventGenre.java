package oop.ticketcenter.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EventGenres")
public class EventGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
}
