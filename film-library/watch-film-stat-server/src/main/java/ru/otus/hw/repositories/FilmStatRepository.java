package ru.otus.hw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.models.FilmStat;

import java.util.Optional;

public interface FilmStatRepository extends JpaRepository<FilmStat,Long> {

    Optional<FilmStat> findFilmStatByTitle(String title);
}