INSERT INTO authors (author_id, full_name)
    VALUES (1,'Author_1'), (2,'Author_2'), (3,'Author_3') ON CONFLICT (author_id) DO NOTHING;

INSERT INTO genres (genre_id, name)
    VALUES (1,'Genre_1'), (2, 'Genre_2'), (3, 'Genre_3') ON CONFLICT (genre_id) DO NOTHING;

INSERT INTO films (title, genre_id, author_id, year, rating)
    VALUES
           ('FilmTitle_1', 1, 1, 2020, 9),
           ('FilmTitle_2', 2, 2, 2000, 8.5),
           ('FilmTitle_3', 3, 3, 1993, 10) ON CONFLICT (title) DO NOTHING;
