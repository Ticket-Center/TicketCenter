package oop.ticketcenter.core.exceptions;

public class SeatTypeDoesNotExistException extends RuntimeException {
    public SeatTypeDoesNotExistException(String seatTypeDoesNotExist) {
        super(seatTypeDoesNotExist);
    }
}
