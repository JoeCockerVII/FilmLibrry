package ru.otus.hw.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmUpdateDto {

    @NotNull
    private Long id;

    @NotBlank(message = "Title should not be blank")
    @Size(min = 2, max = 30, message = "Title should be between 2 and 30")
    private String title;

    @NotNull
    private Long authorId;

    @NotNull
    private Long genreId;

    @NotNull
    @Positive
    private long filmYear;

    @NotNull
    @Positive
    @Max(value = 10)
    private Double rating;
}
