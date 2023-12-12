package oop.ticketcenter.persistence.repositories;

import oop.ticketcenter.persistence.entities.EventGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EventGenreRepository extends JpaRepository<EventGenre, UUID> {
    Optional<EventGenre> findEventGenreByName(String genre);
}