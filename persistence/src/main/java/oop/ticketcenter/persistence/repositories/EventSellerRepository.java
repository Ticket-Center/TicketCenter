package oop.ticketcenter.persistence.repositories;

import oop.ticketcenter.persistence.entities.Event;
import oop.ticketcenter.persistence.entities.EventSeller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface EventSellerRepository extends JpaRepository<EventSeller, UUID> {
    Optional<EventSeller> findEventSellerByUsernameAndPassword(String username, String password);
    Optional<EventSeller> findEventSellerByUsernameAndPasswordKey(String username, String passkey);
    Optional<EventSeller> findEventSellerByUsername(String username);

    Set<EventSeller> findEventSellerByEvents(Event event);

}