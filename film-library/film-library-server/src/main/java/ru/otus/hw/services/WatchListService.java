package ru.otus.hw.services;

import ru.otus.hw.models.dto.WatchListCreateDto;
import ru.otus.hw.models.dto.WatchListDto;

public interface WatchListService {

    WatchListDto create(WatchListCreateDto dto);

}
