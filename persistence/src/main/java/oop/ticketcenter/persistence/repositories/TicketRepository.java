package oop.ticketcenter.persistence.repositories;

import oop.ticketcenter.persistence.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {
}