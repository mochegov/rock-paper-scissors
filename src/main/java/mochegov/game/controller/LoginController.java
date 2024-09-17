package mochegov.game.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mochegov.game.service.PlayerLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final PlayerLoginService playerLoginService;

    @GetMapping("/")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/")
    public String login(@RequestParam("playerName") String playerName, Model model) {
        return playerLoginService.login(playerName)
            .map(errorMessage -> {
                model.addAttribute("message", errorMessage);
                return "login";
            }).orElseGet(() -> "redirect:/main");
    }
}
