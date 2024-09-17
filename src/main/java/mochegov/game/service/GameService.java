package mochegov.game.service;

import io.vavr.control.Try;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mochegov.game.db.model.Game;
import mochegov.game.db.model.Move;
import mochegov.game.db.model.Player;
import mochegov.game.db.repository.GameRepository;
import mochegov.game.enums.GameState;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;

    private final MoveService moveService;
    private final PlayerService playerService;

    @Value("${game.points-advantage-required-to-win}")
    private int pointsAdvantageRequiredToWin;

    /**
     * @param playerName player name
     * @return If there is an unfinished game with the current player, returns it. If there is none, creates a new game.
     */
    public Game getGameByPlayerName(String playerName) {
        return Try.of(() -> gameRepository.findByPlayerName(playerName))
            .map(games -> games.stream()
                .filter(game -> game.getGameState().equals(GameState.RUN))
                .findFirst())
            .getOrElseThrow(throwable -> new RuntimeException("An error occurred while searching for games"))
            .orElseGet(() -> Try.of(() -> gameRepository.addGame(playerName))
                .getOrElseThrow(throwable -> new RuntimeException(
                    "An error occurred while creating the game: %s".formatted(throwable.getMessage())))
                .orElseThrow(() -> new RuntimeException("Failed to create new game")));
    }

    public Game getGameById(int gameId) {
        return Try.of(() -> gameRepository.findById(gameId))
            .getOrElseThrow(throwable -> new RuntimeException(
                "An error occurred while searching for a game by id: %s".formatted(throwable.getMessage())))
            .orElseThrow(() -> new RuntimeException("Game not found by id"));
    }

    @Transactional
    public void restartGameIfOver(int gameId) {
        Game game = getGameById(gameId);

        List<Move> moves = moveService.getGameMoves(game.getId());

        Map<String, Integer> mapScore = Move.getScore(moves);
        int myScore = mapScore.get("myScore");
        int playersScore = mapScore.get("playersScore");

        if (Math.abs(myScore - playersScore) >= pointsAdvantageRequiredToWin) {
            gameRepository.updateState(game.getId(), GameState.COMPLETED);
            Player player = playerService.getPlayerByName(game.getPlayerName());
            if (myScore > playersScore) {
                playerService.addLoss(player);
            } else {
                playerService.addWin(player);
            }
        }
    }
}
