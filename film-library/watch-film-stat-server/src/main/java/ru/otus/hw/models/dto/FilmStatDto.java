package ru.otus.hw.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmStatDto {

    private Long id;

    private String title;

    private Long filmYear;

    private Double rating;

    private Long favoritesCounter;

}
