package ru.otus.hw.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;
import ru.otus.hw.models.Author;
import ru.otus.hw.models.Film;
import ru.otus.hw.models.Genre;

import java.util.List;
import java.util.stream.IntStream;


@DisplayName("Film repository test (JPA)")
@DataJpaTest
class FilmRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FilmRepository filmRepository;

    private List<Author> dbAuthors;

    private List<Genre> dbGenres;

    private List<Film> dbFilms;

    private static final long initYear = 2019L;
    private static final double initRating = 8.0;

    @BeforeEach
    void setUp() {
        dbAuthors = getDbAuthors();
        dbGenres = getDbGenres();
        dbFilms = getDbFilms(dbAuthors, dbGenres);
    }

    @DisplayName("Should load film by id")
    @Test
    void shouldReturnCorrectFilmById() {
        var expectedFilm = dbFilms.get(0);
        var actualFilm = filmRepository.findById(expectedFilm.getId()).get();
        assertThat(actualFilm)
                .usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expectedFilm);
    }

    @DisplayName("Should load list of films")
    @Test
    void shouldReturnCorrectFilmsList() {
        var actualFilms = filmRepository.findAll();
        var expectedFilms = dbFilms;

        assertThat(actualFilms)
                .usingRecursiveComparison()
                .isEqualTo(expectedFilms);
        actualFilms.forEach(System.out::println);
    }

    @DisplayName("Should save new film")
    @Test
    void shouldSaveNewFilm() {
        var expectedFilm = new Film(1L, "FilmTitle_10500", dbAuthors.get(0), dbGenres.get(0), initYear, initRating);
        var returnedFilm = filmRepository.save(expectedFilm);
        assertThat(returnedFilm).isNotNull()
                .matches(film -> film.getId() > 0)
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(expectedFilm);

        assertThat(filmRepository.findById(returnedFilm.getId()))
                .isPresent()
                .get()
                .isEqualTo(returnedFilm);
    }

    @DisplayName("Should save updated film")
    @Test
    void shouldSaveUpdatedFilm() {
        var expectedFilm = new Film(1L, "FilmTitle_10500", dbAuthors.get(2), dbGenres.get(2), initYear + 1 , initRating +0.5);

        assertThat(filmRepository.findById(expectedFilm.getId()))
                .isPresent()
                .get()
                .isNotEqualTo(expectedFilm);

        var returnedFilm = filmRepository.save(expectedFilm);
        assertThat(returnedFilm).isNotNull()
                .matches(film -> film.getId() > 0)
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(expectedFilm);

        assertThat(filmRepository.findById(returnedFilm.getId()))
                .isPresent()
                .get()
                .isEqualTo(returnedFilm);
    }

    @DisplayName("Should delete film by id ")
    @Test
    void shouldDeleteFilm() {
        assertThat(filmRepository.findById(1L)).isPresent();
        filmRepository.deleteById(1L);
        assertThat(filmRepository.findById(1L)).isEmpty();
    }

    private static List<Author> getDbAuthors() {
        return IntStream.range(1, 4).boxed()
                .map(id -> new Author(id.longValue(), "Author_" + id))
                .toList();
    }

    private static List<Genre> getDbGenres() {
        return IntStream.range(1, 4).boxed()
                .map(id -> new Genre(id.longValue(), "Genre_" + id))
                .toList();
    }

    private static List<Film> getDbFilms(List<Author> dbAuthors, List<Genre> dbGenres) {
        return IntStream.range(1, 4).boxed()
                .map(id -> new Film(id.longValue(), "FilmTitle_" + id,
                        dbAuthors.get(id - 1),
                        dbGenres.get(id - 1),
                        initYear + (id - 1),
                        initRating + (id - 1)
                        )
                )
                .toList();
    }

//    private static List<Film> getDbFilms() {
//        var dbAuthors = getDbAuthors();
//        var dbGenres = getDbGenres();
//        return getDbFilms(dbAuthors, dbGenres);
//    }
}