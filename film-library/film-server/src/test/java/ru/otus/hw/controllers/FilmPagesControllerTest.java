package ru.otus.hw.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw.controllers.pages.FilmPagesController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(FilmPagesController.class)
class FilmPagesControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Should return list view page")
    public void shouldReturnListPage() throws Exception {

        mvc.perform(get("/film/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("film-list"));
    }

    @Test
    @DisplayName("Should return edit view page")
    public void shouldReturnEditPage() throws Exception {

        mvc.perform(get("/film/edit/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("film-edit"))
                .andExpect(model().attribute("id", 1L));
    }

    @Test
    @DisplayName("Should return create view page")
    public void shouldReturnCreatePage() throws Exception {

        mvc.perform(get("/film/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("film-create"));
    }

}