package ru.otus.hw.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.otus.hw.models.dto.FilmNotifyDto;
import ru.otus.hw.services.KafkaProducerService;

@Service
@Slf4j
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private final KafkaTemplate<String, FilmNotifyDto> kafkaTemplate;

    private final String topic;

    public KafkaProducerServiceImpl(KafkaTemplate<String, FilmNotifyDto> kafkaTemplate,
                                    @Value("${application.kafka.topic}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void send(FilmNotifyDto dto) {
        kafkaTemplate.send(topic, dto);
        log.info("Send message :{}", dto.toString());
    }

}