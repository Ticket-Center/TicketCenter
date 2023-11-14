package oop.ticketcenter.persistence.repositories;

import oop.ticketcenter.persistence.entities.EventOrganizator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventOrganizatorRepository extends JpaRepository<EventOrganizator, UUID> {
}