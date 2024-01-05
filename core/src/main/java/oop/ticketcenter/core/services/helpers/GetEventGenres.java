package oop.ticketcenter.core.services.helpers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import oop.ticketcenter.persistence.repositories.EventGenreRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetEventGenres {
    private final EventGenreRepository eventGenreRepository;
    private final List<String> genres = new ArrayList<>();

    public List<String> getGenres(){
        genres.clear();
        genres.addAll(eventGenreRepository.findAll().stream().map(eventGenre -> eventGenre.getName()).toList());
        return genres;
    }
}
