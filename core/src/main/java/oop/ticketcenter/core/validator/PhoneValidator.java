package oop.ticketcenter.core.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhoneValidator implements InputValidator<String> {

    private static final int REQUIRED_LENGTH = 10;

    @Override
    public boolean isValid(String input) {
        return input != null && input.length() == REQUIRED_LENGTH && input.matches("\\d+");
    }
}
