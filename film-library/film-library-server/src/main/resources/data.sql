INSERT INTO authors (full_name)
    VALUES ('Author_1'), ('Author_2'), ('Author_3') ON CONFLICT (author_id) DO NOTHING;

INSERT INTO genres (name)
    VALUES ('Genre_1'), ('Genre_2'), ('Genre_3') ON CONFLICT (genre_id) DO NOTHING;

INSERT INTO films (title, genre_id, author_id, year, rating)
    VALUES
           ('FilmTitle_1', 1, 1, 2020, 9),
           ('FilmTitle_2', 2, 2, 2000, 8.5),
           ('FilmTitle_3', 3, 3, 1993, 10) ON CONFLICT (film_id) DO NOTHING;