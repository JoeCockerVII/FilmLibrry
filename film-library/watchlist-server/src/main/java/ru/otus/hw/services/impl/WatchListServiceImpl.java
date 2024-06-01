package ru.otus.hw.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.exceptions.NotFoundException;
import ru.otus.hw.exceptions.dto.EntityAlreadyExistException;
import ru.otus.hw.feign.FilmServiceProxy;
import ru.otus.hw.models.dto.FilmNotifyDto;
import ru.otus.hw.models.dto.watchlist.WatchFilmAddRequestDto;
import ru.otus.hw.models.dto.watchlist.WatchFilmAddResponseDto;
import ru.otus.hw.models.dto.watchlist.WatchListCreateRequestDto;
import ru.otus.hw.models.dto.watchlist.WatchListResponseDto;
import ru.otus.hw.models.dto.watchlist.WatchListDto;
import ru.otus.hw.models.dto.watchlist.WatchListUpdateDto;
import ru.otus.hw.models.mappers.FilmMapper;
import ru.otus.hw.models.mappers.WatchListMapper;
import ru.otus.hw.repositories.UserRepository;
import ru.otus.hw.repositories.WatchListRepository;
import ru.otus.hw.services.WatchListService;
import ru.otus.hw.services.KafkaProducerService;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class WatchListServiceImpl implements WatchListService {

    private final WatchListRepository watchListRepository;

    private final UserRepository userRepository;

    private final FilmServiceProxy filmService;

    private final KafkaProducerService kafkaProducerService;

    private final WatchListMapper mapper;

    private final FilmMapper filmMapper;

    @Transactional(readOnly = true)
    @Override
    public WatchListDto findById(long id) {
        var watchList = watchListRepository.findById(id).orElseThrow(NotFoundException::new);
        return mapper.toWatchListDto(watchList);
    }

    @Transactional(readOnly = true)
    @Override
    public Set<WatchListResponseDto> findAll() {
        var name = getAuthParams().getName();
        return watchListRepository.findAllByUserUsername(name)
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

    @Override
    public WatchListResponseDto update(long id, WatchListUpdateDto dto) {
        var watchList = watchListRepository.findById(id).orElseThrow(NotFoundException::new);
        watchList.setTitle(dto.getTitle());

        return mapper.toDto(watchListRepository.save(watchList));
    }

    @Transactional
    @Override
    public void delete(long id) {
        watchListRepository.deleteById(id);
    }

    @Transactional
    @Override
    public WatchFilmAddResponseDto addFilmToWatchList(long id, WatchFilmAddRequestDto dto) {
        var watchList = watchListRepository.findById(id).orElseThrow(NotFoundException::new);
        var film = filmMapper.toModel(filmService.findByTitle(dto.getTitle()));

        if (!watchList.isFilmExist(film.getTitle())) {
            watchList.addFilms(film);
            watchListRepository.save(watchList);

            var notifyDto = FilmNotifyDto.builder()
                    .filmTitle(film.getTitle())
                    .year(film.getYear())
                    .rating(film.getRating())
                    .added(LocalDateTime.now())
                    .build();

            kafkaProducerService.send(notifyDto);
            return mapper.toDto(film);
        } else {
            throw new EntityAlreadyExistException("Entity already exist");
        }
    }

    @Transactional
    @Override
    public void deleteFilmFromWatchList(long watchId, long filmId) {
        var watchList = watchListRepository.findById(watchId).orElseThrow(NotFoundException::new);
        var film = filmMapper.toModel(filmService.findById(filmId));
        watchList.removeFilm(film);
    }

    private Authentication getAuthParams() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}