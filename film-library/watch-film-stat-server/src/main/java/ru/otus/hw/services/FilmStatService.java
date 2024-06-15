package ru.otus.hw.services;

import ru.otus.hw.models.dto.FilmNotifyDto;
import ru.otus.hw.models.dto.FilmStatDto;

import java.util.List;

public interface FilmStatService {

    List<FilmStatDto> findAll();

    void saveOrUpdate(FilmNotifyDto dto);

    void deleteById(long id);

}
