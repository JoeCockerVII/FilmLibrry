package ru.otus.hw.services;

import ru.otus.hw.models.dto.FilmNotifyDto;


public interface KafkaConsumerService {

    void listen(FilmNotifyDto dto);

}
