package oop.ticketcenter.core.services.helpers;

import lombok.RequiredArgsConstructor;
import oop.ticketcenter.persistence.entities.EventGenre;
import oop.ticketcenter.persistence.repositories.EventGenreRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetEventGenres {
    private final EventGenreRepository eventGenreRepository;
}
