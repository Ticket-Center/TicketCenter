package oop.ticketcenter.core.interfaces.users.rating;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorResult;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingSellerResult implements ProcessorResult {
    private boolean successful;
}
