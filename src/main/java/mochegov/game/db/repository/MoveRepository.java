package mochegov.game.db.repository;

import static mochegov.game.db.jooq.domain.tables.Game.GAME;
import static mochegov.game.db.jooq.domain.tables.Move.MOVE;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import mochegov.game.db.model.Move;
import mochegov.game.enums.Sign;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.Table;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class MoveRepository extends BaseRepository<Move> {
    public MoveRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    protected RecordMapper<Record, Move> getRecordMapper() {
        return rec -> Move.builder()
            .id(rec.get(MOVE.ID))
            .gameId(rec.get(MOVE.GAME_ID))
            .mySign(Sign.valueOf(rec.get(MOVE.MY_SIGN)))
            .playersSign(Sign.valueOf(rec.get(MOVE.PLAYERS_SIGN)))
            .myResult(rec.get(MOVE.MY_RESULT))
            .playersResult(rec.get(MOVE.PLAYERS_RESULT))
            .createdAt(rec.get(GAME.CREATED_AT))
            .build();
    }

    @Override
    protected Table<?> getTable() {
        return MOVE;
    }

    public List<Move> getGameMoves(int gameId) {
        return dslContext.select()
            .from(MOVE)
            .where(MOVE.GAME_ID.eq(gameId))
            .orderBy(MOVE.ID)
            .fetch()
            .map(recordMapper);
    }

    public Optional<Move> addMove(Move move) {
        return insert(Map.of(
            MOVE.GAME_ID, move.getGameId(),
            MOVE.MY_SIGN, move.getMySign(),
            MOVE.PLAYERS_SIGN, move.getPlayersSign(),
            MOVE.MY_RESULT, move.getMyResult(),
            MOVE.PLAYERS_RESULT, move.getPlayersResult()
        ));
    }
}
