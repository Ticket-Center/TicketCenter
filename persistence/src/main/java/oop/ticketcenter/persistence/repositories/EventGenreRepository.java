package oop.ticketcenter.persistence.repositories;

import oop.ticketcenter.persistence.entities.EventGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventGenreRepository extends JpaRepository<EventGenre, UUID> {
}