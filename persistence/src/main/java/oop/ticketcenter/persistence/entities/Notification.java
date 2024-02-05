package oop.ticketcenter.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import oop.ticketcenter.persistence.enums.Roles;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Length(max = 250, message = "Message should be no longer than 250 symbols")
    private String message;

    @CreationTimestamp
    private Timestamp dateCreated;

    private Boolean isReceived;

    private List<UUID> receivers;
}
