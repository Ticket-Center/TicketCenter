package oop.ticketcenter.persistence.repositories;

import oop.ticketcenter.persistence.entities.PlaceSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PlaceSeatTypeRepository extends JpaRepository<PlaceSeatType, UUID> {
}
