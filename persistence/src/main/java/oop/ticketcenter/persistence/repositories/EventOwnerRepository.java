package oop.ticketcenter.persistence.repositories;

import oop.ticketcenter.persistence.entities.EventOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventOwnerRepository extends JpaRepository<EventOwner, UUID> {
}