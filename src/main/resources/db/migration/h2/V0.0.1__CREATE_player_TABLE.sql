create table player
(
    player_name     varchar(20) primary key,
    number_wins     int not null default 0,
    number_losses   int not null default 0,
    created_at      timestamp default CURRENT_TIMESTAMP,
    updated_at      timestamp
);

comment on table player is 'Players';

comment on column player.player_name is 'Player name';
comment on column player.number_wins is 'Numbers of wins';
comment on column player.number_losses is 'Numbers of losses';
comment on column player.created_at is 'Creation timestamp';
comment on column player.updated_at is 'Update timestamp';