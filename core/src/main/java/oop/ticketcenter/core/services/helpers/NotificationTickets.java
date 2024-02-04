package oop.ticketcenter.core.services.helpers;

import lombok.Getter;

public enum NotificationTickets {
    TWENTY("20%", 0.2),
    FIFTY("50%", 0.5),
    SEVENTY("70%", 0.7),
    NINETY("90%", 0.9),
    HUNDRED("100%", 1.0);

    @Getter
    private String percent;
    @Getter
    private Double equasion;

    private NotificationTickets(String percentage, Double equasion) {
        this.equasion = equasion;
        this.percent = percentage;
    }

}
