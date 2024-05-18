package ru.otus.hw.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ru.otus.hw.models.User;
import ru.otus.hw.models.WatchList;
import ru.otus.hw.models.dto.WatchListCreateDto;
import ru.otus.hw.models.dto.WatchListDto;


@Mapper
public interface WatchListMapper {

    WatchListDto toDto(WatchList watchList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "films", ignore = true)
    WatchList toModel(WatchListCreateDto dto, User user);


}