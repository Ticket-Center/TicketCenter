package oop.ticketcenter.core.exceptions;

public class NotEnoughTicketsException extends RuntimeException {
    public NotEnoughTicketsException(String s) {
        super(s);
    }
}
