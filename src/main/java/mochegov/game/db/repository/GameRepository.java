package mochegov.game.db.repository;

import static mochegov.game.db.jooq.domain.tables.Game.GAME;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import mochegov.game.db.jooq.domain.tables.records.GameRecord;
import mochegov.game.db.model.Game;
import mochegov.game.enums.GameState;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.Table;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class GameRepository extends BaseRepository<Game> {
    public GameRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    protected RecordMapper<Record, Game> getRecordMapper() {
        return rec -> Game.builder()
            .id(rec.get(GAME.ID))
            .playerName(rec.get(GAME.PLAYER_NAME))
            .gameState(GameState.valueOf(rec.get(GAME.GAME_STATE)))
            .createdAt(rec.get(GAME.CREATED_AT))
            .updatedAt(rec.get(GAME.UPDATED_AT))
            .build();
    }

    @Override
    protected Table<?> getTable() {
        return GAME;
    }

    public Optional<Game> findById(int id) {
        return getOne(GAME.ID.eq(id));
    }

    public List<Game> findByPlayerName(@NonNull String playerName) {
        return getList(GAME.PLAYER_NAME.eq(playerName));
    }

    public Optional<Game> addGame(String playerName) {
        return insert(Map.of(
            GAME.PLAYER_NAME, playerName,
            GAME.GAME_STATE, GameState.RUN
        ));
    }

    public Optional<Game> updateState(int id, GameState newState) {
        GameRecord record = new GameRecord();
        record.setId(id);
        record.setGameState(newState.toString());
        record.setUpdatedAt(LocalDateTime.now());
        return update(GAME.ID.eq(id), record);
    }
}
