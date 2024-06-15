create table if not exists authors(
    author_id SERIAL PRIMARY KEY NOT NULL,
    full_name varchar(255)
);

create table if not exists genres(
    genre_id SERIAL PRIMARY KEY NOT NULL,
    name varchar(255)
);

create table if not exists films(
    film_id bigserial,
    title varchar(255),
    author_id bigint references authors(author_id) on delete cascade,
    genre_id bigint references genres(genre_id) on delete cascade,
    film_year bigserial,
    rating real,
    primary key (film_id)
);

create table if not exists comments(
    comment_id SERIAL PRIMARY KEY NOT NULL,
    text varchar(255),
    film_id bigint references films(film_id) on delete cascade
);