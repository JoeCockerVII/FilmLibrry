package ru.otus.hw.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw.models.dto.FilmStatDto;
import ru.otus.hw.services.FilmStatService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FilmStatController {

    private final FilmStatService filmStatService;

    @GetMapping("/stats")
    public List<FilmStatDto> getAllStats() {
        return filmStatService.findAll();
    }

    @DeleteMapping("/stats/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStat(@PathVariable("id") long id) {
        filmStatService.deleteById(id);
    }


}
