package oop.ticketcenter.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Length(min = 16, max = 16, message = "Invalid id length")
    private UUID id;

    private Integer maxTicketsPerPerson;

    private Integer soldTickets;

    // to generate from database
    private Timestamp date;

    @Length(max = 80, message = "Title should be max 80 characters")
    private String title;

    @ManyToOne
    private EventGenre eventGenre;

    @ManyToOne
    private EventType eventType;

    @ManyToOne
    private EventOwner eventOwner;

    @ManyToOne
    private EventOrganizator eventOrganizator;

    @ManyToOne
    private EventPlace eventPlace;
}
