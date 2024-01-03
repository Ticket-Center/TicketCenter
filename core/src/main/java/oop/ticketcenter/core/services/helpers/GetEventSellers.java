package oop.ticketcenter.core.services.helpers;

import lombok.Getter;
import oop.ticketcenter.persistence.repositories.EventSellerRepository;

import java.util.List;


public class GetEventSellers {
    private EventSellerRepository eventSellerRepository;

    private static GetEventSellers getEventSellers = null;
    @Getter
    private final List<String> sellers;

    private GetEventSellers(){
        this.sellers = eventSellerRepository.findAll().stream().map(eventSeller -> eventSeller.getUsername()).toList();
    }
    public static GetEventSellers getInstance(){
        if(getEventSellers == null){
            getEventSellers = new GetEventSellers();
        }
        return getEventSellers;
    }

}
