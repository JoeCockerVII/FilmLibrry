INSERT INTO authors (author_id, full_name)
    VALUES (1,'Christopher Nolan'),
           (2,'Robert Zemeckis'),
           (3,'Quentin Tarantino'),
           (4,'Martin Scorsese'),
           (5,'Darren Aronofsky'),
           (6,'Roman Polanski'),
           (7,'Peter Weir'),
           (8,'John McTiernan')
           ON CONFLICT (author_id) DO NOTHING;

INSERT INTO genres (genre_id, name)
    VALUES (1,'Action'),
           (2,'Comedy-Drama'),
           (3,'Comedy'),
           (4,'Sci-Fi'),
           (5,'Drama'),
           (6,'Thriller')
           ON CONFLICT (genre_id) DO NOTHING;

INSERT INTO films (title, genre_id, author_id, film_year, rating)
    VALUES
           ('Dark Knight', 1, 1, 2008, 9.0),
           ('Forrest Gump', 2, 2, 1994, 8.8),
           ('Django Unchained', 3, 3, 2012, 8.5),
           ('Interstellar', 4, 1, 2014, 8.7),
           ('Gangs of New York', 5, 4, 2002, 7.5),
           ('Black Swan', 6, 5, 2010, 8.0),
           ('Pianist', 5, 6, 2002, 8.5),
           ('Oppenheimer', 5, 1, 2024, 8.3),
           ('Truman Show', 5, 7, 1998, 8.2),
           ('Die Hard', 1, 8, 1988, 8.2),
           ('Shutter Island', 6, 4, 2010, 8.2)
           ON CONFLICT (title) DO NOTHING;


--