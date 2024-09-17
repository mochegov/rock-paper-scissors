package mochegov.game.db.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.Table;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public abstract class BaseRepository<M> {
    protected final DSLContext dslContext;

    protected Table<?> table;
    protected RecordMapper<Record, M> recordMapper;

    protected BaseRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
        this.table = getTable();
        this.recordMapper = getRecordMapper();
    }

    protected abstract RecordMapper<Record, M> getRecordMapper();

    protected abstract Table<?> getTable();

    public Optional<M> getOne(@NonNull Condition... conditions) {
        return Optional.ofNullable(dslContext.select().from(table).where(conditions).fetchOne())
            .map(rec -> rec.map(recordMapper));
    }

    public List<M> getList(@NonNull Condition condition) {
        return dslContext.select().from(table).where(condition).fetch().map(recordMapper);
    }

    protected Optional<M> insert(@NonNull Map<Field<?>, Object> fieldValueMap) {
        return Optional.ofNullable(dslContext.insertInto(table)
            .set(fieldValueMap)
            .returning()
            .fetchOne()).map(rec -> rec.map(recordMapper));
    }

    protected Optional<M> update(@NonNull Condition condition, @NonNull Record jooqRecord) {
        return Optional.ofNullable(dslContext.update(table)
            .set(jooqRecord)
            .where(condition)
            .returning()
            .fetchOne()).map(rec -> rec.map(recordMapper));
    }

    protected int delete(@NonNull Condition condition) {
        return dslContext.delete(table)
            .where(condition)
            .execute();
    }
}
