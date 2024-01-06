package oop.ticketcenter.core.services.helpers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.exceptions.UserNotFoundException;
import oop.ticketcenter.persistence.entities.*;
import oop.ticketcenter.persistence.enums.Roles;
import oop.ticketcenter.persistence.repositories.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Notifications {

    private final NotificationRepository notificationRepository;
    private final EventSellerRepository eventSellerRepository;

    private List<Notification> notifications = new ArrayList<>();

    private List<Notification> fetchAllNotifications() {
        notifications.clear();
        notifications.addAll(notificationRepository.findAll());
        return notifications;
    }

    public void removeReceivedNotifications() {
        fetchAllNotifications();
        notifications.forEach(notification -> {
            if (notification.getIsReceived().equals(true)) {
                notificationRepository.delete(notification);
            }
        });
    }

    public Set<String> checkForNotifications(UUID id) {
        List<Notification> notificationList = fetchAllNotifications();
        for (int i = 0; i < notificationList.size(); i++) {
            if (notificationList.get(i).getIsReceived().equals(true)) {
                notificationList.remove(notificationList.get(i));
            }
            if (!notificationList.get(i).getReceivers().contains(id)) {
                notificationList.remove(notificationList.get(i));
            }
        }
        if (notificationList.isEmpty()) {
            return Set.of("No notifications");
        }
        Set<String> receivedNotifications = new HashSet<>();
        notificationList.forEach(notification -> {
            notification.setIsReceived(true);
            notificationRepository.save(notification);
            receivedNotifications.add(notification.getMessage());
        });
        return receivedNotifications;
    }

    public void quantityNotifications(int quantity, Event event) {
        String message = "";
        if (event.getSoldTickets() == (quantity * NotificationTickets.HUNDRED.getEquasion())) { // 100%
            message = "Congratulations! " + NotificationTickets.HUNDRED.getPercent() + " of the tickets for " + event.getTitle() + " have been sold";
        } else if (event.getSoldTickets() == (quantity * NotificationTickets.NINETY.getEquasion())) { // 90%
            message = "Congratulations! " + NotificationTickets.NINETY.getPercent() + " of the tickets for " + event.getTitle() + " have been sold";
        } else if (event.getSoldTickets() == (quantity * NotificationTickets.SEVENTY.getEquasion())) { // 70%
            message = "Congratulations! " + NotificationTickets.SEVENTY.getPercent() + "  of the tickets for " + event.getTitle() + " have been sold";
        } else if (event.getSoldTickets() == (quantity * NotificationTickets.FIFTY.getEquasion())) { // 50%
            message = "Congratulations! " + NotificationTickets.FIFTY.getPercent() + "  of the tickets for " + event.getTitle() + " have been sold";
        } else if (event.getSoldTickets() == (quantity * NotificationTickets.TWENTY.getEquasion())) { // 20%
            message = "Congratulations! " + NotificationTickets.TWENTY.getPercent() + "  of the tickets for " + event.getTitle() + " have been sold";
        } else {
            smallQuantitySoldTicketsNotification(event);
            return;
        }
        Notification notification = Notification.builder()
                .message(message)
                .isReceived(false)
                .receivers(List.of(event.getEventOwner().getId()))
                .build();
        notificationRepository.save(notification);
    }

    private void smallQuantitySoldTicketsNotification(Event event) {
        List<UUID> usersToBeNotified = new ArrayList<>();
        if (event.getDate().before(Date.from(Instant.now().plus(25, ChronoUnit.DAYS)))) {
            if (ActiveUserSingleton.getInstance().getUserRole().equals(Roles.SELLER)) {
                usersToBeNotified = eventSellerRepository.findEventSellerByEvents(event).stream()
                        .map(seller -> seller.getId())
                        .filter(seller -> seller.equals(ActiveUserSingleton.getInstance().getActiveUser()))
                        .toList();
            } else {
                usersToBeNotified.add(event.getEventOwner().getId());
            }
            Notification notification = Notification.builder()
                    .message("Less than " + NotificationTickets.TWENTY.getPercent() + "  of the tickets for " + event.getTitle() + " have been sold")
                    .isReceived(false)
                    .receivers(usersToBeNotified)
                    .build();
            notificationRepository.save(notification);
        }
    }
}
