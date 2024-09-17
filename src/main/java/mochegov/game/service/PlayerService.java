package mochegov.game.service;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mochegov.game.db.model.Player;
import mochegov.game.db.repository.PlayerRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    public Player addPlayerIfNotExists(String playerName) {
        return Try.of(() -> playerRepository.findByName(playerName))
            .getOrElseThrow(throwable -> new RuntimeException(
                "An error occurred while searching for a player by name: %s".formatted(throwable.getMessage())))
            .orElseGet(() -> Try.of(() -> playerRepository.addPlayer(playerName))
                .getOrElseThrow(throwable -> new RuntimeException(
                    "An error occurred while creating a new player: %s".formatted("")))
                .orElseThrow(() -> new RuntimeException("The player was not created")));
    }

    public Player getPlayerByName(String playerName) {
        return Try.of(() -> playerRepository.findByName(playerName))
            .getOrElseThrow(throwable -> new RuntimeException(
                "Error getting player by name: %s".formatted(throwable.getMessage())))
            .orElseThrow(() -> new RuntimeException("Error getting player"));
    }

    public void addWin(Player player) {
        Try.of(() -> playerRepository.updatePlayerStatistics(
                player.getPlayerName(), player.getNumberOfWins() + 1, player.getNumberOfLosses()))
            .getOrElseThrow(throwable -> new RuntimeException(
                "Error updating player statistics: %s".formatted(throwable.getMessage())));
    }

    public void addLoss(Player player) {
        Try.of(() -> playerRepository.updatePlayerStatistics(
                player.getPlayerName(), player.getNumberOfWins(), player.getNumberOfLosses() + 1))
            .getOrElseThrow(throwable -> new RuntimeException(
                "Error updating player statistics: %s".formatted(throwable.getMessage())));
    }
}
