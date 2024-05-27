package ru.otus.hw.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.exceptions.NotFoundException;
import ru.otus.hw.models.dto.FilmCreateDto;
import ru.otus.hw.models.dto.FilmDto;
import ru.otus.hw.models.dto.FilmUpdateDto;
import ru.otus.hw.models.mappers.FilmMapper;
import ru.otus.hw.repositories.AuthorRepository;
import ru.otus.hw.repositories.FilmRepository;
import ru.otus.hw.repositories.GenreRepository;
import ru.otus.hw.services.FilmService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FilmServiceImpl implements FilmService {

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final FilmRepository filmRepository;

    private final FilmMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public FilmDto findById(long id) {
        var film = filmRepository.findById(id).orElseThrow(NotFoundException::new);
        return mapper.toDto(film);
    }

    @Transactional(readOnly = true)
    @Override
    public List<FilmDto> findAll() {
        return filmRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public FilmDto create(FilmCreateDto dto) {

        var author = authorRepository.findById(dto.getAuthorId()).orElseThrow(NotFoundException::new);
        var genre = genreRepository.findById(dto.getGenreId()).orElseThrow(NotFoundException::new);
        var film = mapper.toModel(dto,author,genre);

        return mapper.toDto(filmRepository.save(film));
    }

    @Transactional
    @Override
    public FilmDto update(FilmUpdateDto dto) {

        var film = filmRepository.findById(dto.getId()).orElseThrow(NotFoundException::new);
        var author = authorRepository.findById(dto.getAuthorId()).orElseThrow(NotFoundException::new);
        var genre = genreRepository.findById(dto.getGenreId()).orElseThrow(NotFoundException::new);

        film.setTitle(dto.getTitle());
        film.setAuthor(author);
        film.setGenre(genre);
        film.setYear(dto.getYear());
        film.setRating(dto.getRating());

        return mapper.toDto(filmRepository.save(film));
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        filmRepository.deleteById(id);
    }

    @Override
    @Transactional
    public FilmDto findByTitle(String title) {
        var film = filmRepository.findFilmByTitle(title).orElseThrow(NotFoundException::new);
        return mapper.toDto(film);
    }
}
