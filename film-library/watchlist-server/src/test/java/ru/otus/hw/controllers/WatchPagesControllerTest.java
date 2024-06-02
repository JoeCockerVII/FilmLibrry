package ru.otus.hw.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw.controllers.pages.WatchListPagesController;
import ru.otus.hw.security.SecurityConfiguration;
import ru.otus.hw.services.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(WatchListPagesController.class)
@Import({SecurityConfiguration.class})
@WithMockUser(username = "user", roles = "USER")
class WatchPagesControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService service;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Should return list view page")
    public void shouldReturnListPage() throws Exception {

        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("watch-list"));
    }

    @Test
    @DisplayName("Should return edit view page")
    public void shouldReturnEditPage() throws Exception {

        mvc.perform(get("/watch/edit/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("watch-edit"))
                .andExpect(model().attribute("id", 1L));
    }

    @Test
    @DisplayName("Should return create view page")
    public void shouldReturnCreatePage() throws Exception {

        mvc.perform(get("/watch/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("watchlist-create"));
    }

    @Test
    @DisplayName("Should return info view page")
    public void shouldReturnInfoPage() throws Exception {

        mvc.perform(get("/watch/info/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("watch-info"))
                .andExpect(model().attribute("id", 1L));
    }

    @Test
    @DisplayName("Should return info view page")
    public void shouldReturnAddFilmPage() throws Exception {

        mvc.perform(get("/watch/info/{id}/film", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("watch-film-add"))
                .andExpect(model().attribute("id", 1L));
    }


}