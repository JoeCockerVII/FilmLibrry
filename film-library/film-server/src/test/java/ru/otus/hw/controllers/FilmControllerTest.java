package ru.otus.hw.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw.models.dto.FilmCreateDto;
import ru.otus.hw.models.dto.FilmUpdateDto;
import ru.otus.hw.services.FilmService;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FilmController.class)
class FilmControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FilmService filmService;

    @InjectMocks
    private TestHelper testHelper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should get film list")
    void shouldGetFilmList() throws Exception {
        var films = testHelper.getDbFilms();

        given(filmService.findAll()).willReturn(films);

        mvc.perform(get("/films"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(films)));
    }

    @Test
    @DisplayName("Should create film")
    void shouldCreateFilm() throws Exception {

        var film = new FilmCreateDto("Title",  1L, 1L,2000L,9.99);
        var expected = objectMapper.writeValueAsString(film);

        mvc.perform(post("/films")
                        .contentType(APPLICATION_JSON)
                        .content(expected))
                .andExpect(status().isCreated());

        verify(filmService).create(film);
    }

    @Test
    @DisplayName("Should update film")
    void shouldUpdateFilm() throws Exception {

        var film = new FilmUpdateDto(1L, "Title",  1L, 1L,2000L,9.99);
        var expected = objectMapper.writeValueAsString(film);

        mvc.perform(put("/films/1")
                        .contentType(APPLICATION_JSON)
                        .content(expected))
                .andExpect(status().isOk());

        verify(filmService).update(film);
    }

    @Test
    @DisplayName("Should delete film")
    void shouldDeleteFilm() throws Exception {
        mvc.perform(delete("/films/1"))
                .andExpect(status().isNoContent());
        verify(filmService, times(1)).deleteById(1L);
    }

}