package mochegov.game.db.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mochegov.game.enums.GameState;

@Getter
@Setter
@Builder
public class Game {
    private int id;
    private String playerName;
    private GameState gameState;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
