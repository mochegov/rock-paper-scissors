/*
 * This file is generated by jOOQ.
 */
package mochegov.game.db.jooq.domain.tables.records;


import java.time.LocalDateTime;

import mochegov.game.db.jooq.domain.tables.Move;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * Game moves
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MoveRecord extends UpdatableRecordImpl<MoveRecord> implements Record7<Integer, Integer, String, String, Integer, Integer, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>PUBLIC.MOVE.ID</code>. Unique identifier
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>PUBLIC.MOVE.ID</code>. Unique identifier
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>PUBLIC.MOVE.GAME_ID</code>. Game ID
     */
    public void setGameId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>PUBLIC.MOVE.GAME_ID</code>. Game ID
     */
    public Integer getGameId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>PUBLIC.MOVE.MY_SIGN</code>. My sign (ROCK, PAPER,
     * SCISSORS)
     */
    public void setMySign(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>PUBLIC.MOVE.MY_SIGN</code>. My sign (ROCK, PAPER,
     * SCISSORS)
     */
    public String getMySign() {
        return (String) get(2);
    }

    /**
     * Setter for <code>PUBLIC.MOVE.PLAYERS_SIGN</code>. Players sign (ROCK,
     * PAPER, SCISSORS)
     */
    public void setPlayersSign(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>PUBLIC.MOVE.PLAYERS_SIGN</code>. Players sign (ROCK,
     * PAPER, SCISSORS)
     */
    public String getPlayersSign() {
        return (String) get(3);
    }

    /**
     * Setter for <code>PUBLIC.MOVE.MY_RESULT</code>. My result (0 or 1)
     */
    public void setMyResult(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>PUBLIC.MOVE.MY_RESULT</code>. My result (0 or 1)
     */
    public Integer getMyResult() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>PUBLIC.MOVE.PLAYERS_RESULT</code>. Players result (0 or
     * 1)
     */
    public void setPlayersResult(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>PUBLIC.MOVE.PLAYERS_RESULT</code>. Players result (0 or
     * 1)
     */
    public Integer getPlayersResult() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>PUBLIC.MOVE.CREATED_AT</code>. Creation timestamp
     */
    public void setCreatedAt(LocalDateTime value) {
        set(6, value);
    }

    /**
     * Getter for <code>PUBLIC.MOVE.CREATED_AT</code>. Creation timestamp
     */
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<Integer, Integer, String, String, Integer, Integer, LocalDateTime> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<Integer, Integer, String, String, Integer, Integer, LocalDateTime> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Move.MOVE.ID;
    }

    @Override
    public Field<Integer> field2() {
        return Move.MOVE.GAME_ID;
    }

    @Override
    public Field<String> field3() {
        return Move.MOVE.MY_SIGN;
    }

    @Override
    public Field<String> field4() {
        return Move.MOVE.PLAYERS_SIGN;
    }

    @Override
    public Field<Integer> field5() {
        return Move.MOVE.MY_RESULT;
    }

    @Override
    public Field<Integer> field6() {
        return Move.MOVE.PLAYERS_RESULT;
    }

    @Override
    public Field<LocalDateTime> field7() {
        return Move.MOVE.CREATED_AT;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public Integer component2() {
        return getGameId();
    }

    @Override
    public String component3() {
        return getMySign();
    }

    @Override
    public String component4() {
        return getPlayersSign();
    }

    @Override
    public Integer component5() {
        return getMyResult();
    }

    @Override
    public Integer component6() {
        return getPlayersResult();
    }

    @Override
    public LocalDateTime component7() {
        return getCreatedAt();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public Integer value2() {
        return getGameId();
    }

    @Override
    public String value3() {
        return getMySign();
    }

    @Override
    public String value4() {
        return getPlayersSign();
    }

    @Override
    public Integer value5() {
        return getMyResult();
    }

    @Override
    public Integer value6() {
        return getPlayersResult();
    }

    @Override
    public LocalDateTime value7() {
        return getCreatedAt();
    }

    @Override
    public MoveRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public MoveRecord value2(Integer value) {
        setGameId(value);
        return this;
    }

    @Override
    public MoveRecord value3(String value) {
        setMySign(value);
        return this;
    }

    @Override
    public MoveRecord value4(String value) {
        setPlayersSign(value);
        return this;
    }

    @Override
    public MoveRecord value5(Integer value) {
        setMyResult(value);
        return this;
    }

    @Override
    public MoveRecord value6(Integer value) {
        setPlayersResult(value);
        return this;
    }

    @Override
    public MoveRecord value7(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public MoveRecord values(Integer value1, Integer value2, String value3, String value4, Integer value5, Integer value6, LocalDateTime value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MoveRecord
     */
    public MoveRecord() {
        super(Move.MOVE);
    }

    /**
     * Create a detached, initialised MoveRecord
     */
    public MoveRecord(Integer id, Integer gameId, String mySign, String playersSign, Integer myResult, Integer playersResult, LocalDateTime createdAt) {
        super(Move.MOVE);

        setId(id);
        setGameId(gameId);
        setMySign(mySign);
        setPlayersSign(playersSign);
        setMyResult(myResult);
        setPlayersResult(playersResult);
        setCreatedAt(createdAt);
        resetChangedOnNotNull();
    }
}
