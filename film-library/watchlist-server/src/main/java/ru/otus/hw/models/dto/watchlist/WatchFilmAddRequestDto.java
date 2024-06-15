package ru.otus.hw.models.dto.watchlist;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WatchFilmAddRequestDto {

    @NotBlank(message = "Title should not be blank")
    @Size(min = 2, max = 30, message = "Title should be between 2 and 30")
    private String title;

}
