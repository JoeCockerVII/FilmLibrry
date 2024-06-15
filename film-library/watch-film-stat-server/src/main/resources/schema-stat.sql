create table if not exists filmStats(
    filmstat_id bigserial,
    title varchar(255),
    filmYear bigserial,
    author varchar(255),
    genre varchar(255),
    rating real,
    favoritesCounter bigserial
);