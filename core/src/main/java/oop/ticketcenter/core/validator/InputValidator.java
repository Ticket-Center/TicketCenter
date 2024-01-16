package oop.ticketcenter.core.validator;

public interface InputValidator<T> {
    boolean isValid(T input);
}
