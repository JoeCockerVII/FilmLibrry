package ru.otus.hw.models.dto.watchlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WatchListResponseDto {

    private Long id;

    private String title;

    private String userName;

}
