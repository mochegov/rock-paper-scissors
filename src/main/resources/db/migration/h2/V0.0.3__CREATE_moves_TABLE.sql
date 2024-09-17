create table move
(
    id              int auto_increment primary key,
    game_id         int not null references game (id),
    my_sign         varchar(10) not null,
    players_sign    varchar(10) not null,
    my_result       int not null,
    players_result  int not null,
    created_at      timestamp default CURRENT_TIMESTAMP
);

comment on table move is 'Game moves';

comment on column move.id is 'Unique identifier';
comment on column move.game_id is 'Game ID';
comment on column move.my_sign is 'My sign (ROCK, PAPER, SCISSORS)';
comment on column move.players_sign is 'Players sign (ROCK, PAPER, SCISSORS)';
comment on column move.my_result is 'My result (0 or 1)';
comment on column move.players_result is 'Players result (0 or 1)';
comment on column move.created_at is 'Creation timestamp';