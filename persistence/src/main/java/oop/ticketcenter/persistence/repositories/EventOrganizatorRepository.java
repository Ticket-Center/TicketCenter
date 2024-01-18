package oop.ticketcenter.persistence.repositories;

import oop.ticketcenter.persistence.entities.EventOrganizator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EventOrganizatorRepository extends JpaRepository<EventOrganizator, UUID> {

    Optional<EventOrganizator> findEventOrganizatorByUsernameAndPassword(String username, String password);
    Optional<EventOrganizator> findEventOrganizatorByUsernameAndPasswordKey(String username, String passkey);
    Optional<EventOrganizator> findEventOrganizatorByUsername(String username);
}