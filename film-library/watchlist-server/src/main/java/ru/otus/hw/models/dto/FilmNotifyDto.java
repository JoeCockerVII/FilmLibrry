package ru.otus.hw.models.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FilmNotifyDto {

    private String filmTitle;

    private long filmYear;

    private String author;

    private String genre;

    private Double rating;

    private LocalDateTime added;
}