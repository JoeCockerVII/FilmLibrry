package ru.otus.hw.controllers.pages;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class FilmStatPagesController {

    @GetMapping("/")
    public String listPage() {
        return "film-stat-list";
    }


}
