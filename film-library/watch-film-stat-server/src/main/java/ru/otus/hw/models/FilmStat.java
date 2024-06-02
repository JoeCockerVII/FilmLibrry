package ru.otus.hw.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "filmStats")
public class FilmStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "filmstat_id")
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "filmYear", nullable = false)
    private Long filmYear;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @Column(name = "favoritesCounter", nullable = false)
    private long favoritesCounter = 0L;

}
