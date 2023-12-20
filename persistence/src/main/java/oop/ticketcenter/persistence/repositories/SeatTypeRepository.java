package oop.ticketcenter.persistence.repositories;

import oop.ticketcenter.persistence.entities.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SeatTypeRepository extends JpaRepository<SeatType, UUID> {
    List<SeatType> findAllByEventPlace_Name(String name);
}