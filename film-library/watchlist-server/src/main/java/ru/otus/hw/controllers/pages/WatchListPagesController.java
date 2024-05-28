package ru.otus.hw.controllers.pages;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class WatchListPagesController {

    @GetMapping("/")
    public String listWatchPage() {
        return "watch-list";
    }

    @GetMapping("/watch/create")
    public String createPage() {
        return "watchlist-create";
    }

    @GetMapping("/watch/edit/{id}")
    public String editPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("id", id);
        return "watch-edit";
    }

    @GetMapping("/watch/info/{id}")
    public String infoPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("id", id);
        return "watch-info";
    }

    @GetMapping("/watch/info/{id}/film")
    public String addFilmPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("id", id);
        return "watch-film-add";
    }

}
