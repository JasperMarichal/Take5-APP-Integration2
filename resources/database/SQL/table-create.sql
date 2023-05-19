CREATE TABLE IF NOT EXISTS player
(
    player_id VARCHAR(255) PRIMARY KEY,
    name      VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS game
(
    game_id    VARCHAR(255) PRIMARY KEY,
    start_time TIMESTAMP NOT NULL,
    end_time   TIMESTAMP,
    winner     VARCHAR(255)
);

ALTER TABLE game
    ADD CONSTRAINT fk_player FOREIGN KEY (winner) REFERENCES player (player_id);

CREATE TABLE IF NOT EXISTS round
(
    round_id     VARCHAR(255) PRIMARY KEY,
    game_id      VARCHAR(255) NOT NULL,
    round_number INTEGER      NOT NULL,
    start_time   TIMESTAMP    NOT NULL
);

CREATE TABLE IF NOT EXISTS move
(
    move_id     VARCHAR(255) PRIMARY KEY,
    game_id     VARCHAR(255) NOT NULL,
    round_id    VARCHAR(255) NOT NULL,
    player_id   VARCHAR(255) NOT NULL,
    move_number INTEGER      NOT NULL,
    points      INTEGER,
    end_time    TIMESTAMP    NOT NULL
)