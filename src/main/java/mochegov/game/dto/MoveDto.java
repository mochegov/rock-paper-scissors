package mochegov.game.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mochegov.game.enums.Sign;

@Getter
@Setter
@Builder
public class MoveDto {
    private int gameId;
    private Sign mySign;
    private Sign playersSign;

    public static MoveDto getMove(int gameId) {
        return MoveDto.builder()
            .gameId(gameId)
            .mySign(Sign.getRandom())
            .build();
    }
}
