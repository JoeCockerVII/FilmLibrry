package ru.otus.hw.models.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FilmNotifyDto {

    private String filmTitle;

    private Long filmYear;

    private Double rating;

    private LocalDateTime added;
}