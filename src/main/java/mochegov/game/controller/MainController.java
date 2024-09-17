package mochegov.game.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mochegov.game.db.model.Game;
import mochegov.game.db.model.Move;
import mochegov.game.db.model.Player;
import mochegov.game.dto.MoveDto;
import mochegov.game.service.GameService;
import mochegov.game.service.MoveService;
import mochegov.game.service.PlayerLoginService;
import mochegov.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {
    private final PlayerLoginService playerLoginService;
    private final PlayerService playerService;
    private final GameService gameService;
    private final MoveService moveService;

    @Value("${game.points-advantage-required-to-win}")
    private int pointsAdvantageRequiredToWin;

    @GetMapping("/main")
    public String getMainPage(@RequestParam(required = false) Boolean logout, Model model) {
        Optional.ofNullable(logout)
            .ifPresent(b -> playerLoginService.logout());

        return Optional.ofNullable(playerLoginService.getPlayerName())
            .map(playerName -> {
                Player player = playerService.addPlayerIfNotExists(playerLoginService.getPlayerName());
                model.addAttribute("playerName", player.getPlayerName());
                model.addAttribute("numberOfWins", player.getNumberOfWins());
                model.addAttribute("numberOfLosses", player.getNumberOfLosses());

                model.addAttribute("pointsAdvantageRequiredToWin", pointsAdvantageRequiredToWin);

                Game game = gameService.getGameByPlayerName(player.getPlayerName());
                List<Move> moves = moveService.getGameMoves(game.getId());
                model.addAttribute("moves", moves);

                Map<String, Integer> mapScore = Move.getScore(moves);
                model.addAttribute("myScore", mapScore.get("myScore"));
                model.addAttribute("playersScore", mapScore.get("playersScore"));

                MoveDto moveDto = MoveDto.getMove(game.getId());
                model.addAttribute("move", moveDto);

                return "main";
            })
            .orElse("redirect:/");
    }

    @PostMapping("/main/move")
    public String makeMove(@ModelAttribute("move") MoveDto moveDto) {
        moveService.makeMove(moveDto);
        gameService.restartGameIfOver(moveDto.getGameId());
        return "redirect:/main";
    }
}
