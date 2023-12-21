package oop.ticketcenter.core.services.helpers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import oop.ticketcenter.persistence.entities.EventGenre;
import oop.ticketcenter.persistence.repositories.EventGenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public class GetEventGenres {
    private static GetEventGenres getEventGenres = null;
    private EventGenreRepository eventGenreRepository;

    @Getter
    private List<String> genres;

    private GetEventGenres(){
        this.genres = eventGenreRepository.findAll().stream().map(eventGenre -> eventGenre.getName()).toList();
    }

    public GetEventGenres getInstance(){
        if(getEventGenres == null){
            getEventGenres = new GetEventGenres();
        }
        return getEventGenres;
    }
}
