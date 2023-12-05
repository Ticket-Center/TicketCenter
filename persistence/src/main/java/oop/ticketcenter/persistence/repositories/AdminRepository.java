package oop.ticketcenter.persistence.repositories;

import oop.ticketcenter.persistence.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
    Optional<Admin> findAdminByUsernameAndPassword(String admin, String password);
}