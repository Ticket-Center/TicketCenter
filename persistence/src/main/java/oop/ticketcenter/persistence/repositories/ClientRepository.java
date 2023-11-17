package oop.ticketcenter.persistence.repositories;

//import oop.ticketcenter.persistence.entities.Client;
import oop.ticketcenter.persistence.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

}