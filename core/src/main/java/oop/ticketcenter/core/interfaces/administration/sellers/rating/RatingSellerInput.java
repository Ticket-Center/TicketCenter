package oop.ticketcenter.core.interfaces.administration.sellers.rating;

import lombok.*;
import oop.ticketcenter.core.interfaces.base.ProcessorInput;
import oop.ticketcenter.persistence.enums.Rating;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingSellerInput implements ProcessorInput {
    private String sellerName;
    private Rating rating;
}
