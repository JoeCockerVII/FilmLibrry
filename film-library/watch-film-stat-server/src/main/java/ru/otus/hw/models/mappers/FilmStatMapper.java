package ru.otus.hw.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.hw.models.FilmStat;
import ru.otus.hw.models.dto.FilmNotifyDto;
import ru.otus.hw.models.dto.FilmStatDto;


@Mapper
public interface FilmStatMapper {

    FilmStatDto toDto(FilmStat film);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "favoritesCounter", ignore = true)
    @Mapping(target = "title", source = "filmTitle")
    FilmStat toModel(FilmNotifyDto dto);

}