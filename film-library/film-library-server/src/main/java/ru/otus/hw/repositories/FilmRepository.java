package ru.otus.hw.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.models.Film;

import java.util.List;
import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film,Long> {

    @EntityGraph(value = "author-genre-entity-graph")
    Optional<Film> findById(long id);

    @EntityGraph(value = "author-genre-entity-graph")
    List<Film> findAll();
}