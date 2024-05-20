package ru.otus.hw.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw.models.dto.watchlist.WatchFilmAddRequestDto;
import ru.otus.hw.models.dto.watchlist.WatchFilmAddResponseDto;
import ru.otus.hw.models.dto.watchlist.WatchListCreateRequestDto;
import ru.otus.hw.models.dto.watchlist.WatchListResponseDto;
import ru.otus.hw.models.dto.watchlist.WatchListDto;
import ru.otus.hw.services.WatchListService;

import java.util.Set;


@RestController
@RequiredArgsConstructor
public class WatchListController {

    private final WatchListService watchService;

    @GetMapping(value = "/watchlists/{id}")
    public WatchListDto getWatchlistById(@PathVariable long id) {
        return watchService.findById(id);
    }

    @GetMapping(value = "/watchlists")
    public Set<WatchListResponseDto> getAllWatchlist() {
        return watchService.findAll();
    }

    @PostMapping(value = "/watchlists")
    @ResponseStatus(HttpStatus.CREATED)
    public WatchListResponseDto createWatchlist(@Valid @RequestBody WatchListCreateRequestDto dto) {
        return watchService.create(dto);
    }

    @PostMapping(value = "/watchlists/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public WatchFilmAddResponseDto addFilmToWatchList(@PathVariable long id,
                                                      @Valid @RequestBody WatchFilmAddRequestDto dto) {
        return watchService.addFilmToWatchList(id, dto);
    }

    @DeleteMapping(value = "/watchlists/{watchId}/film/{filmId}")
    public void deleteFilmFromWatchList(@PathVariable long watchId, @PathVariable long filmId) {
        watchService.deleteFilmFromWatchList(watchId, filmId);
    }


}


//    @PostMapping("/sign-up")
//    public String addUser(@RequestBody User user) {
//        userService.create(user);
//        return "User is saved";
//    }
