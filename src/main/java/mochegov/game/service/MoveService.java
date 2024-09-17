package mochegov.game.service;

import io.vavr.control.Try;
import java.util.List;
import java.util.Map;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mochegov.game.db.model.Move;
import mochegov.game.db.repository.MoveRepository;
import mochegov.game.dto.MoveDto;
import mochegov.game.enums.Sign;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MoveService {
    private final MoveRepository moveRepository;

    private final static Map<Map<Sign, Sign>, Sign> GAME_RULES = Map.of(
        Map.of(Sign.ROCK, Sign.PAPER), Sign.PAPER,
        Map.of(Sign.ROCK, Sign.SCISSORS), Sign.ROCK,
        Map.of(Sign.PAPER, Sign.SCISSORS), Sign.SCISSORS,
        Map.of(Sign.PAPER, Sign.ROCK), Sign.PAPER,
        Map.of(Sign.SCISSORS, Sign.ROCK), Sign.ROCK,
        Map.of(Sign.SCISSORS, Sign.PAPER), Sign.SCISSORS
    );

    public List<Move> getGameMoves(int gameId) {
        return Try.of(() -> moveRepository.getGameMoves(gameId))
            .getOrElseThrow(throwable -> new RuntimeException(
                "Error while getting game moves: %s".formatted(throwable.getMessage())));
    }

    public Move makeMove(MoveDto moveDto) {
        Map<String, Integer> mapScore = getMapScore(moveDto.getMySign(), moveDto.getPlayersSign());
        Move move = Move.builder()
            .gameId(moveDto.getGameId())
            .mySign(moveDto.getMySign())
            .playersSign(moveDto.getPlayersSign())
            .myResult(mapScore.get("myScore"))
            .playersResult(mapScore.get("playersScore"))
            .build();

        return Try.of(() -> moveRepository.addMove(move))
            .getOrElseThrow(throwable -> new RuntimeException(
                "An error occurred while saving the game move: %s".formatted(throwable.getMessage())))
            .orElseThrow(() -> new RuntimeException("Failed to save game move"));
    }

    private Map<String, Integer> getMapScore(@NonNull Sign mySign, @NonNull Sign playersSign) {
        if (mySign.equals(playersSign)) {
            return Map.of("myScore", 0, "playersScore", 0);
        } else {
            int myScore = GAME_RULES.get(Map.of(mySign, playersSign)).equals(mySign) ? 1 : 0;
            int playersScore = myScore == 0 ? 1 : 0;
            return Map.of(
                "myScore", myScore,
                "playersScore", playersScore
            );
        }
    }
}
