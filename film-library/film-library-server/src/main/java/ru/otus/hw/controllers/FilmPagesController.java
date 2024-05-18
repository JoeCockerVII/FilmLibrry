package ru.otus.hw.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class FilmPagesController {

    @GetMapping("/film/list")
    public String listPage() {
        return "film-list";
    }

    @GetMapping("/film/edit/{id}")
    public String editPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("id", id);
        return "film-edit";
    }

    @GetMapping("/film/create")
    public String createPage() {
        return "film-create";
    }


}
