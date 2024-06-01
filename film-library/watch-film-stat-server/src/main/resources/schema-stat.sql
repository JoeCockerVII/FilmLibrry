create table if not exists filmStats(
    filmstat_id bigserial,
    title varchar(255),
    year bigserial,
    rating real,
    favoritesCounter bigserial
);