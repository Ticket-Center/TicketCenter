package oop.ticketcenter.core.services.helpers;

import lombok.Getter;
import oop.ticketcenter.persistence.repositories.EventGenreRepository;

import java.util.List;

public class GetEventGenres {
    private static GetEventGenres getEventGenres = null;
    private EventGenreRepository eventGenreRepository;

    @Getter
    private final List<String> genres;

    private GetEventGenres(){
        this.genres = eventGenreRepository.findAll().stream().map(eventGenre -> eventGenre.getName()).toList();
    }

    public static GetEventGenres getInstance(){
        if(getEventGenres == null){
            getEventGenres = new GetEventGenres();
        }
        return getEventGenres;
    }
}
