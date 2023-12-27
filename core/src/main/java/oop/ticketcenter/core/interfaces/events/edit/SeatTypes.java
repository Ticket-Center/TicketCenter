package oop.ticketcenter.core.interfaces.events.edit;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatTypes {
    private String type;
    private Double price;
}
