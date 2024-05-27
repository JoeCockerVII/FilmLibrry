package ru.otus.hw.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.otus.hw.models.dto.FilmCreateDto;
import ru.otus.hw.models.dto.FilmDto;
import ru.otus.hw.models.dto.FilmUpdateDto;
import ru.otus.hw.services.FilmService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @GetMapping("/films")
    public List<FilmDto> getFilms() {
        return filmService.findAll();
    }

    @PostMapping("/films")
    @ResponseStatus(HttpStatus.CREATED)
    public FilmDto create(@Valid @RequestBody FilmCreateDto filmCreateDto) {
        return filmService.create(filmCreateDto);
    }

    @PutMapping("/films/{id}")
    public FilmDto update(@PathVariable("id") long id, @Valid @RequestBody FilmUpdateDto filmUpdateDto) {
        filmUpdateDto.setId(id);
        return filmService.update(filmUpdateDto);
    }

    @DeleteMapping("/films/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        filmService.deleteById(id);
    }

    @GetMapping("/films/{id}")
    public FilmDto getById(@PathVariable("id") long id) {
        return filmService.findById(id);
    }

    @GetMapping("/films/title/{title}")
    public FilmDto getByTitle(@PathVariable("title") String title) {
        return filmService.findByTitle(title);
    }

}
