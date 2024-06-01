package ru.otus.hw.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.otus.hw.models.dto.FilmNotifyDto;
import ru.otus.hw.services.FilmStatService;
import ru.otus.hw.services.KafkaConsumerService;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private final FilmStatService filmStatService;

    @KafkaListener(topics = "${application.kafka.topic}", containerFactory = "listenerContainerFactory")
    public void listen(FilmNotifyDto dto) {
        log.info("--- Film  :{} ", dto.toString());
        filmStatService.saveOrUpdate(dto);
        log.info("--- Film Added ---");
    }

}