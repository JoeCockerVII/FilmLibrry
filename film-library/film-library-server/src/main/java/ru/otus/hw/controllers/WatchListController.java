package ru.otus.hw.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw.models.dto.WatchListCreateDto;
import ru.otus.hw.models.dto.WatchListDto;
import ru.otus.hw.services.WatchListService;

import java.security.Principal;


@RestController
@RequiredArgsConstructor
public class WatchListController {

    private final WatchListService watchService;

    @GetMapping(value = "/watchlists")
    public String getWatchlist(Principal principal) {
        return principal.getName();
    }

    @PostMapping(value = "/watchlists")
    @ResponseStatus(HttpStatus.CREATED)
    public WatchListDto createWatchlist(@Valid @RequestBody WatchListCreateDto watchCreateDto) {
        return watchService.create(watchCreateDto);
    }

}
