package ru.otus.hw.services;

import ru.otus.hw.models.dto.watchlist.WatchFilmAddRequestDto;
import ru.otus.hw.models.dto.watchlist.WatchFilmAddResponseDto;
import ru.otus.hw.models.dto.watchlist.WatchListCreateRequestDto;
import ru.otus.hw.models.dto.watchlist.WatchListResponseDto;
import ru.otus.hw.models.dto.watchlist.WatchListDto;
import ru.otus.hw.models.dto.watchlist.WatchListUpdateDto;

import java.util.Set;

public interface WatchListService {

    WatchListDto findById(long id);

    Set<WatchListResponseDto> findAll();

    WatchListResponseDto create(WatchListCreateRequestDto dto);

    WatchListResponseDto update(long id, WatchListUpdateDto dto);

    void delete(long id);

    WatchFilmAddResponseDto addFilmToWatchList(long id, WatchFilmAddRequestDto dto);

    void deleteFilmFromWatchList(long watchId, long filmId);

}
