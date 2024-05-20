package ru.otus.hw.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.exceptions.NotFoundException;
import ru.otus.hw.models.dto.watchlist.WatchFilmAddRequestDto;
import ru.otus.hw.models.dto.watchlist.WatchFilmAddResponseDto;
import ru.otus.hw.models.dto.watchlist.WatchListCreateRequestDto;
import ru.otus.hw.models.dto.watchlist.WatchListResponseDto;
import ru.otus.hw.models.dto.watchlist.WatchListDto;
import ru.otus.hw.models.mappers.WatchListMapper;
import ru.otus.hw.repositories.FilmRepository;
import ru.otus.hw.repositories.UserRepository;
import ru.otus.hw.repositories.WatchListRepository;
import ru.otus.hw.services.WatchListService;

import java.util.Set;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class WatchListServiceImpl implements WatchListService {

    private final WatchListRepository watchListRepository;

    private final UserRepository userRepository;

    private final FilmRepository filmRepository;

    private final WatchListMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public WatchListDto findById(long id) {
        var watchList = watchListRepository.findById(id).orElseThrow(NotFoundException::new);
        return mapper.toWatchListDto(watchList);
    }

    @Transactional(readOnly = true)
    @Override
    public Set<WatchListResponseDto> findAll() {
        return watchListRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toSet());
    }

    @Transactional
    @Override
    public WatchListResponseDto create(WatchListCreateRequestDto dto) {

        var userName = getAuthParams().getName();
        var user = userRepository.findByUsername(userName).orElseThrow(NotFoundException::new);
        var watchList = mapper.toModel(dto, user);

        return mapper.toDto(watchListRepository.save(watchList));
    }

    @Transactional
    @Override
    public WatchFilmAddResponseDto addFilmToWatchList(long id, WatchFilmAddRequestDto dto) {
        var watchList = watchListRepository.findById(id).orElseThrow(NotFoundException::new);
        var film = filmRepository.findFilmByTitle(dto.getTitle()).orElseThrow(NotFoundException::new);
        watchList.addFilms(film);
        watchListRepository.save(watchList);

        return mapper.toDto(film);
    }

    @Transactional
    @Override
    public void deleteFilmFromWatchList(long watchId, long filmId) {
        var watchList = watchListRepository.findById(watchId).orElseThrow(NotFoundException::new);
        var film = filmRepository.findById(filmId).orElseThrow(NotFoundException::new);
        watchList.removeFilm(film);
    }

    private Authentication getAuthParams() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}