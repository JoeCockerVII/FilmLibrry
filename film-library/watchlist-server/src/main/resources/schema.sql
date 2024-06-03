create table if not exists roles (
     id SERIAL PRIMARY KEY NOT NULL,
     name varchar(255)
);

create table if not exists users (
    user_id SERIAL PRIMARY KEY NOT NULL,
    username varchar(255),
    password varchar(255),
    role_id bigint references roles(id)
);

create table if not exists watchlist (
    watchlist_id SERIAL PRIMARY KEY NOT NULL,
    title varchar(255),
    user_id bigint references users(user_id) on delete cascade
);