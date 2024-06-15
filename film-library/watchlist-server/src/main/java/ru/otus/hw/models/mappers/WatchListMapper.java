package ru.otus.hw.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ru.otus.hw.models.Film;
import ru.otus.hw.models.User;
import ru.otus.hw.models.WatchList;
import ru.otus.hw.models.dto.watchlist.WatchFilmAddResponseDto;
import ru.otus.hw.models.dto.watchlist.WatchFilmDto;
import ru.otus.hw.models.dto.watchlist.WatchListCreateRequestDto;
import ru.otus.hw.models.dto.watchlist.WatchListResponseDto;
import ru.otus.hw.models.dto.watchlist.WatchListDto;

import java.util.Set;


@Mapper
public interface WatchListMapper {

    @Mapping(target = "userName", source = "user.username")
    WatchListResponseDto toDto(WatchList watchList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "films", ignore = true)
    WatchList toModel(WatchListCreateRequestDto dto, User user);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    WatchFilmAddResponseDto toDto(Film dto);

    @Mapping(target = "userName", source = "user.username")
    WatchListDto toWatchListDto(WatchList watchList);

    Set<WatchFilmDto> toWatchFilmDto(Set<Film> films);

}