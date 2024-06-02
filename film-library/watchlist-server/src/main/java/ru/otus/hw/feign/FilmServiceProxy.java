package ru.otus.hw.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.otus.hw.models.dto.AuthorDto;
import ru.otus.hw.models.dto.FilmCreateDto;
import ru.otus.hw.models.dto.FilmDto;
import ru.otus.hw.models.dto.FilmUpdateDto;
import ru.otus.hw.models.dto.GenreDto;

import java.util.List;

@FeignClient(name = "film-server", contextId = "films")
public interface FilmServiceProxy {

    @CircuitBreaker(name = "film-server", fallbackMethod = "getDefaultFilms")
    @GetMapping("/films")
    List<FilmDto> findAll();

    @CircuitBreaker(name = "film-server")
    @PostMapping("/films")
    @ResponseStatus(HttpStatus.CREATED)
    FilmDto create(@Valid @RequestBody FilmCreateDto filmCreateDto);

    @CircuitBreaker(name = "film-server")
    @PutMapping("/films/{id}")
    FilmDto update(@PathVariable("id") long id, @Valid @RequestBody FilmUpdateDto filmUpdateDto);

    @CircuitBreaker(name = "film-server")
    @DeleteMapping("/films/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") long id);

    @CircuitBreaker(name = "film-server")
    @GetMapping("/films/{id}")
    FilmDto findById(@PathVariable("id") long id);

    @CircuitBreaker(name = "film-server")
    @GetMapping("/films/title/{title}")
    FilmDto findByTitle(@PathVariable("title") String title);


    default List<FilmDto> getDefaultFilms(Throwable throwable) {
        return List.of(
                new FilmDto(101L,"Title_101",
                        new AuthorDto(101L,"Author_101"),
                        new GenreDto(101L, "Genre_101"),
                        2000L,9.8D
                ),
                new FilmDto(102L,"Title_102",
                        new AuthorDto(102L,"Author_102"),
                        new GenreDto(102L, "Genre_102"),
                        2001L,9.99D
                ),
                new FilmDto(103L,"Title_103",
                        new AuthorDto(103L,"Author_103"),
                        new GenreDto(103L, "Genre_103"),
                        2002L,10D
        )
        );
    }

}
