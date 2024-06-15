package ru.otus.hw.services;

import ru.otus.hw.models.dto.FilmNotifyDto;

public interface KafkaProducerService {
    void send(FilmNotifyDto dto);
}
