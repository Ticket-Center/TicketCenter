package oop.ticketcenter.core.exceptions;

public class FilterByDateException extends RuntimeException {
    public FilterByDateException(String seatTypeDoesNotExist) {
        super(seatTypeDoesNotExist);
    }
}
