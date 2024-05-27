
INSERT INTO roles (id, name)
    VALUES (1, 'ADMIN'),  (2, 'USER') ON CONFLICT (id) DO NOTHING;

INSERT INTO users (username, password, role_id)
    VALUES  ('user', '$2a$12$SIs.hJKuWxsVlsPNVN53/eKMEb4Ks97ikMe64hCJrr8GKPQt2hbIK',2),
            ('admin', '$2a$12$we6cdYeikeAH.cpaOwzum.xtm1f2jHLHmaLrtdEw5JpnoJ/8x4Iz2',1)
    ON CONFLICT (username) DO NOTHING;


INSERT INTO watchlist (title, user_id)
    VALUES ('WatchList 1', 2),
           ('WatchList 2', 2)
    ON CONFLICT (title) DO NOTHING;