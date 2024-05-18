package ru.otus.hw.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class WatchListPagesController {


    @GetMapping("/watch/create")
    public String createPage() {
        return "watchlist-create";
    }


}
