package oop.ticketcenter.core.validators;

public interface InputValidator<T> {
    boolean isValid(T input);
}
