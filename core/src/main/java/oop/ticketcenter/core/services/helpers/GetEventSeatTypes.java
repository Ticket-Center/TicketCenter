package oop.ticketcenter.core.services.helpers;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.persistence.entities.PlaceSeatType;
import oop.ticketcenter.persistence.entities.SeatType;
import oop.ticketcenter.persistence.repositories.PlaceSeatTypeRepository;
import oop.ticketcenter.persistence.repositories.SeatTypeRepository;
import org.springframework.stereotype.Component;

import java.util.*;


//public class GetEventSeatTypes {
//    private static GetEventSeatTypes getEventSeatTypes = null;
//
//    private static Set<SeatType> seatTypes;
//    private SeatTypeRepository seatTypeRepository;
//    private GetEventSeatTypes() {
//        seatTypes =  new HashSet<>(seatTypeRepository.findAll());
//    }
//
//    public static GetEventSeatTypes getInstance(){
//        if(getEventSeatTypes == null){
//            getEventSeatTypes = new GetEventSeatTypes();
//        }
//        return getEventSeatTypes;
//    }
//
//    public Map<String, Integer> getSeatTypes(String eventPlace) {
//        Map<String, Integer> result = new HashMap<>();
//        seatTypes.forEach(seatType ->{
//            /*if(seatType.getEventPlace().getName().equals(eventPlace)){
//                result.put(seatType.getType(), seatType.getQuantity());
//            }*/
//        });
//        return result;
//    }
//
//}


@Component
@RequiredArgsConstructor
public class GetEventSeatTypes {
    private List<PlaceSeatType> seatTypes = new ArrayList<>();
    private final SeatTypeRepository seatTypeRepository;
    private final PlaceSeatTypeRepository placeSeatTypeRepository;

    public Map<String, Integer> getSeatTypes(String eventPlace) {
        seatTypes.clear();
        seatTypes.addAll(placeSeatTypeRepository.findPlaceSeatTypeByEventPlace_Name(eventPlace));
        Map<String, Integer> result = new HashMap<>();
        seatTypes.forEach(placeSeatType -> {
            result.put(placeSeatType.getSeatType().getType(), placeSeatType.getQuantity());
        });

        return result;
    }
}


