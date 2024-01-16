package oop.ticketcenter.core.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordValidator implements InputValidator<String>{
    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 255;

    @Override
    public boolean isValid(String input) {
        return input != null && input.length() >= MIN_LENGTH && input.length() <= MAX_LENGTH;
    }
}
