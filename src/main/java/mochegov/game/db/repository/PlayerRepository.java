package mochegov.game.db.repository;

import static mochegov.game.db.jooq.domain.tables.Player.PLAYER;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import mochegov.game.db.jooq.domain.tables.records.PlayerRecord;
import mochegov.game.db.model.Player;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.Table;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class PlayerRepository extends BaseRepository<Player> {
    public PlayerRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    protected RecordMapper<Record, Player> getRecordMapper() {
        return rec -> Player.builder()
            .playerName(rec.get(PLAYER.PLAYER_NAME))
            .numberOfWins(rec.get(PLAYER.NUMBER_WINS))
            .numberOfLosses(rec.get(PLAYER.NUMBER_LOSSES))
            .createdAt(rec.get(PLAYER.CREATED_AT))
            .updatedAt(rec.get(PLAYER.UPDATED_AT))
            .build();
    }

    @Override
    protected Table<?> getTable() {
        return PLAYER;
    }

    public Optional<Player> findByName(@NonNull String playerName) {
        return getOne(PLAYER.PLAYER_NAME.eq(playerName));
    }

    public Optional<Player> addPlayer(String playerName) {
        return insert(Map.of(PLAYER.PLAYER_NAME, playerName));
    }

    public Optional<Player> updatePlayerStatistics(@NonNull String playerName, int numberOfWins, int numberOfLosses) {
        PlayerRecord record = new PlayerRecord();
        record.setPlayerName(playerName);
        record.setNumberWins(numberOfWins);
        record.setNumberLosses(numberOfLosses);
        record.setUpdatedAt(LocalDateTime.now());
        return update(PLAYER.PLAYER_NAME.eq(playerName), record);
    }
}
