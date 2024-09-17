package mochegov.game.service;

import java.util.Optional;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Slf4j
@Service
@SessionScope
@Getter
public class PlayerLoginService {
    private String playerName;

    public Optional<String> login(String playerName) {
        if (playerName.trim().isEmpty()) {
            return Optional.of("Invalid player name");
        }
        this.playerName = playerName;
        return Optional.empty();
    }

    public void logout() {
        this.playerName = null;
    }
}
