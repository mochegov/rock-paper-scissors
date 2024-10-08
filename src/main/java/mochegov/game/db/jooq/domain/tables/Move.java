/*
 * This file is generated by jOOQ.
 */
package mochegov.game.db.jooq.domain.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import mochegov.game.db.jooq.domain.Keys;
import mochegov.game.db.jooq.domain.Public;
import mochegov.game.db.jooq.domain.tables.records.MoveRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function7;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row7;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * Game moves
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Move extends TableImpl<MoveRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>PUBLIC.MOVE</code>
     */
    public static final Move MOVE = new Move();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MoveRecord> getRecordType() {
        return MoveRecord.class;
    }

    /**
     * The column <code>PUBLIC.MOVE.ID</code>. Unique identifier
     */
    public final TableField<MoveRecord, Integer> ID = createField(DSL.name("ID"), SQLDataType.INTEGER.nullable(false).identity(true), this, "Unique identifier");

    /**
     * The column <code>PUBLIC.MOVE.GAME_ID</code>. Game ID
     */
    public final TableField<MoveRecord, Integer> GAME_ID = createField(DSL.name("GAME_ID"), SQLDataType.INTEGER.nullable(false), this, "Game ID");

    /**
     * The column <code>PUBLIC.MOVE.MY_SIGN</code>. My sign (ROCK, PAPER,
     * SCISSORS)
     */
    public final TableField<MoveRecord, String> MY_SIGN = createField(DSL.name("MY_SIGN"), SQLDataType.VARCHAR(10).nullable(false), this, "My sign (ROCK, PAPER, SCISSORS)");

    /**
     * The column <code>PUBLIC.MOVE.PLAYERS_SIGN</code>. Players sign (ROCK,
     * PAPER, SCISSORS)
     */
    public final TableField<MoveRecord, String> PLAYERS_SIGN = createField(DSL.name("PLAYERS_SIGN"), SQLDataType.VARCHAR(10).nullable(false), this, "Players sign (ROCK, PAPER, SCISSORS)");

    /**
     * The column <code>PUBLIC.MOVE.MY_RESULT</code>. My result (0 or 1)
     */
    public final TableField<MoveRecord, Integer> MY_RESULT = createField(DSL.name("MY_RESULT"), SQLDataType.INTEGER.nullable(false), this, "My result (0 or 1)");

    /**
     * The column <code>PUBLIC.MOVE.PLAYERS_RESULT</code>. Players result (0 or
     * 1)
     */
    public final TableField<MoveRecord, Integer> PLAYERS_RESULT = createField(DSL.name("PLAYERS_RESULT"), SQLDataType.INTEGER.nullable(false), this, "Players result (0 or 1)");

    /**
     * The column <code>PUBLIC.MOVE.CREATED_AT</code>. Creation timestamp
     */
    public final TableField<MoveRecord, LocalDateTime> CREATED_AT = createField(DSL.name("CREATED_AT"), SQLDataType.LOCALDATETIME(6).defaultValue(DSL.field(DSL.raw("CURRENT_TIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "Creation timestamp");

    private Move(Name alias, Table<MoveRecord> aliased) {
        this(alias, aliased, null);
    }

    private Move(Name alias, Table<MoveRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("Game moves"), TableOptions.table());
    }

    /**
     * Create an aliased <code>PUBLIC.MOVE</code> table reference
     */
    public Move(String alias) {
        this(DSL.name(alias), MOVE);
    }

    /**
     * Create an aliased <code>PUBLIC.MOVE</code> table reference
     */
    public Move(Name alias) {
        this(alias, MOVE);
    }

    /**
     * Create a <code>PUBLIC.MOVE</code> table reference
     */
    public Move() {
        this(DSL.name("MOVE"), null);
    }

    public <O extends Record> Move(Table<O> child, ForeignKey<O, MoveRecord> key) {
        super(child, key, MOVE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<MoveRecord, Integer> getIdentity() {
        return (Identity<MoveRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<MoveRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_24;
    }

    @Override
    public List<ForeignKey<MoveRecord, ?>> getReferences() {
        return Arrays.asList(Keys.CONSTRAINT_243);
    }

    private transient Game _game;

    /**
     * Get the implicit join path to the <code>PUBLIC.GAME</code> table.
     */
    public Game game() {
        if (_game == null)
            _game = new Game(this, Keys.CONSTRAINT_243);

        return _game;
    }

    @Override
    public Move as(String alias) {
        return new Move(DSL.name(alias), this);
    }

    @Override
    public Move as(Name alias) {
        return new Move(alias, this);
    }

    @Override
    public Move as(Table<?> alias) {
        return new Move(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Move rename(String name) {
        return new Move(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Move rename(Name name) {
        return new Move(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Move rename(Table<?> name) {
        return new Move(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<Integer, Integer, String, String, Integer, Integer, LocalDateTime> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function7<? super Integer, ? super Integer, ? super String, ? super String, ? super Integer, ? super Integer, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function7<? super Integer, ? super Integer, ? super String, ? super String, ? super Integer, ? super Integer, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
