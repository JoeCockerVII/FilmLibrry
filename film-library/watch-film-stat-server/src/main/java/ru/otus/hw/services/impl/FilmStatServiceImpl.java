package ru.otus.hw.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.models.dto.FilmNotifyDto;
import ru.otus.hw.models.dto.FilmStatDto;
import ru.otus.hw.models.mappers.FilmStatMapper;
import ru.otus.hw.repositories.FilmStatRepository;
import ru.otus.hw.services.FilmStatService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FilmStatServiceImpl implements FilmStatService {

    private final FilmStatRepository filmStatRepository;

    private final FilmStatMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public List<FilmStatDto> findAll() {
        return filmStatRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void saveOrUpdate(FilmNotifyDto dto) {
        var filmStat = filmStatRepository.findFilmStatByTitle(dto.getFilmTitle()).orElse(mapper.toModel(dto));
        filmStat.setFavoritesCounter(filmStat.getFavoritesCounter() + 1L);
        filmStatRepository.save(filmStat);
    }

    @Override
    public void deleteById(long id) {
        filmStatRepository.deleteById(id);
    }

}
