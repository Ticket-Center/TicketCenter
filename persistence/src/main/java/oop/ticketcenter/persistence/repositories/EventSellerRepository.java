package oop.ticketcenter.persistence.repositories;

import oop.ticketcenter.persistence.entities.EventSeller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventSellerRepository extends JpaRepository<EventSeller, UUID> {
}