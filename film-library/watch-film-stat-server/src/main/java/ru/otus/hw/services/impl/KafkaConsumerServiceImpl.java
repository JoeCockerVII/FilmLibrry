package ru.otus.hw.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.otus.hw.models.dto.FilmNotifyDto;
import ru.otus.hw.services.KafkaConsumerService;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    @KafkaListener(topics = "${application.kafka.topic}", containerFactory = "listenerContainerFactory")
    public void listen(FilmNotifyDto dto) {
        log.info("-------------- Film Added :{} -------------- ", dto.toString());

    }

}