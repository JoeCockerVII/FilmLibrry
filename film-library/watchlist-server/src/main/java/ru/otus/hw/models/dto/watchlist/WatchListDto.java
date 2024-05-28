package ru.otus.hw.models.dto.watchlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.hw.models.dto.FilmDto;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WatchListDto {

    private Long id;

    private String title;

    private String userName;

    private Set<FilmDto> films;

}
