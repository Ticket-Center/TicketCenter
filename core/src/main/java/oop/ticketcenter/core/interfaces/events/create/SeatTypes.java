package oop.ticketcenter.core.interfaces.events.create;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatTypes {
    private String type;
    private Integer quantity;
}
