package oop.ticketcenter.core.exceptions;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(String s) {
        super(s);
    }
}
