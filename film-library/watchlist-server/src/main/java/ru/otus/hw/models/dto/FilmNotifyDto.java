package ru.otus.hw.models.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FilmNotifyDto {

    private String filmTitle;

    private Long year;

    private Double rating;

    private LocalDateTime added;
}