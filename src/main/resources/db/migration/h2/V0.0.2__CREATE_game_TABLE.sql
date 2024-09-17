create table game
(
    id              int auto_increment primary key,
    player_name     varchar(20) not null references player (player_name),
    game_state      varchar(10) not null,
    created_at      timestamp default CURRENT_TIMESTAMP,
    updated_at      timestamp
);

comment on table game is 'Games';

comment on column game.id is 'Unique identifier';
comment on column game.player_name is 'Name of player';
comment on column game.game_state is 'State of game';
comment on column game.created_at is 'Creation timestamp';
comment on column game.updated_at is 'Update timestamp';