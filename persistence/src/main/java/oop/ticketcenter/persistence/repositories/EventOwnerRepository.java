package oop.ticketcenter.persistence.repositories;

import oop.ticketcenter.persistence.entities.EventOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EventOwnerRepository extends JpaRepository<EventOwner, UUID> {
    Optional<EventOwner> findEventOwnerByUsernameAndPassword(String username, String password);
    Optional<EventOwner> findEventOwnerByUsernameAndPasswordKey(String username, String passkey);
    Optional<EventOwner> findEventOwnerByUsername(String username);
}