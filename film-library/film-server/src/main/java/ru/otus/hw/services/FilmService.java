package ru.otus.hw.services;

import ru.otus.hw.models.Film;
import ru.otus.hw.models.dto.FilmCreateDto;
import ru.otus.hw.models.dto.FilmDto;
import ru.otus.hw.models.dto.FilmUpdateDto;

import java.util.List;

public interface FilmService {
    Film findById(long id);

    List<FilmDto> findAll();

    FilmDto create(FilmCreateDto dto);

    FilmDto update(FilmUpdateDto dto);

    void deleteById(long id);

    Film findFilmByTitle(String title);
}
