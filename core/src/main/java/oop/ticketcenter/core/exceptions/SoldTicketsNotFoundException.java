package oop.ticketcenter.core.exceptions;

public class SoldTicketsNotFoundException extends RuntimeException {
    public SoldTicketsNotFoundException(String soldTicketsNotFound) {
        super(soldTicketsNotFound);
    }
}
