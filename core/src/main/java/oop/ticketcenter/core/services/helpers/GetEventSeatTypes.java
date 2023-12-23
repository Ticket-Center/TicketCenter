package oop.ticketcenter.core.services.helpers;

import oop.ticketcenter.persistence.entities.SeatType;
import oop.ticketcenter.persistence.repositories.SeatTypeRepository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GetEventSeatTypes {
    private static GetEventSeatTypes getEventSeatTypes = null;

    private static Set<SeatType> seatTypes;
    private static SeatTypeRepository seatTypeRepository;
    private GetEventSeatTypes() {
        this.seatTypes =  new HashSet<>(seatTypeRepository.findAll());
    }

    public static GetEventSeatTypes getInstance(){
        if(getEventSeatTypes == null){
            getEventSeatTypes = new GetEventSeatTypes();
        }
        return getEventSeatTypes;
    }

    public Map<String, Integer> getSeatTypes(String eventPlace) {
        Map<String, Integer> result = new HashMap<>();
        seatTypes.forEach(seatType ->{
            /*if(seatType.getEventPlace().getName().equals(eventPlace)){
                result.put(seatType.getType(), seatType.getQuantity());
            }*/
        });
        return result;
    }

}
