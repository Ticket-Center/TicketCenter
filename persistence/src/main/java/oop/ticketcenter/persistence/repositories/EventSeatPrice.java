package oop.ticketcenter.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventSeatPrice extends JpaRepository<EventSeatPrice, UUID> {
}
