package oop.ticketcenter.core.services.helpers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import oop.ticketcenter.persistence.repositories.EventGenreRepository;
import oop.ticketcenter.persistence.repositories.EventSellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public class GetEventSellers {
    private EventSellerRepository eventSellerRepository;

    private static GetEventSellers getEventSellers = null;
    @Getter
    private List<String> sellers;

    private GetEventSellers(){
        this.sellers = eventSellerRepository.findAll().stream().map(eventSeller -> eventSeller.getUsername()).toList();
    }
    public GetEventSellers getInstance(){
        if(getEventSellers == null){
            getEventSellers = new GetEventSellers();
        }
        return getEventSellers;
    }

}
