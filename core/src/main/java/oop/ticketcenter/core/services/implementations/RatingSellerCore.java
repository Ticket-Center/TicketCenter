package oop.ticketcenter.core.services.implementations;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.exceptions.UserNotFoundException;
import oop.ticketcenter.core.interfaces.users.rating.RatingSeller;
import oop.ticketcenter.core.interfaces.users.rating.RatingSellerInput;
import oop.ticketcenter.core.interfaces.users.rating.RatingSellerResult;
import oop.ticketcenter.persistence.entities.EventSeller;
import oop.ticketcenter.persistence.enums.Rating;
import oop.ticketcenter.persistence.repositories.EventSellerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingSellerCore implements RatingSeller {
    private final EventSellerRepository eventSellerRepository;

    @Override
    public RatingSellerResult process(RatingSellerInput input) {
        EventSeller seller = eventSellerRepository.findEventSellerByUsername(input.getSellerName())
                .orElseThrow(() -> new UserNotFoundException("Seller with this username is not found"));

        seller.setRating(calculateAverageRating(seller.getRating(), input.getRating()));
        eventSellerRepository.save(seller);

        return RatingSellerResult.builder().successful(true).build();
    }

    private Rating calculateAverageRating(Rating currentRating, Rating newRating) {
        double currentRatingValue = currentRating.getValue();
        double newRatingValue = newRating.getValue();
        double averageRatingValue = (currentRatingValue + newRatingValue) / 2.0;
        int roundedAverageRatingValue = (int) Math.round(averageRatingValue);
        return Rating.getByValue(roundedAverageRatingValue);
    }
}
