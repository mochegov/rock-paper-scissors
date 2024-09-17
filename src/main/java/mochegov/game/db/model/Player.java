package mochegov.game.db.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Player {
    private String playerName;
    private int numberOfWins;
    private int numberOfLosses;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
