package ru.otus.hw.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.hw.models.Author;
import ru.otus.hw.models.Film;
import ru.otus.hw.models.Genre;
import ru.otus.hw.models.dto.FilmCreateDto;
import ru.otus.hw.models.dto.FilmDto;


@Mapper
public interface FilmMapper {

    FilmDto toDto(Film film);

    @Mapping(target = "id", ignore = true)
    Film toModel(FilmCreateDto dto, Author author, Genre genre);

    Film toModel(FilmDto dto);

}