create table if not exists filmStats(
    filmstat_id bigserial,
    title varchar(255),
    filmYear bigserial,
    rating real,
    favoritesCounter bigserial
);