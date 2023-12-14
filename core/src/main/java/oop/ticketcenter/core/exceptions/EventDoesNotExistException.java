package oop.ticketcenter.core.exceptions;

public class EventDoesNotExistException extends RuntimeException {
    public EventDoesNotExistException(String s) {
        super(s);
    }
}
