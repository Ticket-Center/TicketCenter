package oop.ticketcenter.persistence.repositories;

import oop.ticketcenter.persistence.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    //List<Notification> findAllByReceiversIsContaining(UUID id);
    //findNotificationsByReceiversContaining(UUID id);
}