package mochegov.game.db.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mochegov.game.enums.Sign;

@Getter
@Setter
@Builder
public class Move {
    private int id;
    private int gameId;
    private Sign mySign;
    private Sign playersSign;
    private int myResult;
    private int playersResult;
    private LocalDateTime createdAt;

    public static Map<String, Integer> getScore(List<Move> moves) {
        int myScore = moves.stream().mapToInt(Move::getMyResult).sum();
        int playersScore = moves.stream().mapToInt(Move::getPlayersResult).sum();
        return Map.of(
            "myScore", myScore,
            "playersScore", playersScore
        );
    }

    public static void setIdInOrder(List<Move> moves) {
        int i = 1;
        for (Move move : moves) {
            move.setId(i++);
        }
    }
}
