package ru.otus.hw.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.exceptions.NotFoundException;
import ru.otus.hw.models.dto.WatchListCreateDto;
import ru.otus.hw.models.dto.WatchListDto;
import ru.otus.hw.models.mappers.WatchListMapper;
import ru.otus.hw.repositories.UserRepository;
import ru.otus.hw.repositories.WatchListRepository;
import ru.otus.hw.services.WatchListService;


@RequiredArgsConstructor
@Service
public class WatchListServiceImpl implements WatchListService {

    private final WatchListRepository watchListRepository;

    private final UserRepository userRepository;

    private final WatchListMapper mapper;


    @Transactional
    @Override
    public WatchListDto create(WatchListCreateDto dto) {

        var auth = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByUsername(auth.getName()).orElseThrow(NotFoundException::new);

        var watchList = mapper.toModel(dto, user);

        return mapper.toDto(watchListRepository.save(watchList));
    }

}