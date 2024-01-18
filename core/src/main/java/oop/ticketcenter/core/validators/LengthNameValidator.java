package oop.ticketcenter.core.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LengthNameValidator implements InputValidator<String>{
    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 60;
    @Override
    public boolean isValid(String input) {
        return input != null && input.length() >= MIN_LENGTH && input.length() <= MAX_LENGTH;
    }
}
