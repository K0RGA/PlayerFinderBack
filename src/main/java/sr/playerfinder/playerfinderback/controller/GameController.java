package sr.playerfinder.playerfinderback.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sr.playerfinder.playerfinderback.service.helper.GamesProcessingHelper;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class GameController {

    private final GamesProcessingHelper gamesProcessingHelper;

    @GetMapping("/getGames")
    public ResponseEntity<String> getAllGames() {
        log.info("Request: all games");
        return ResponseEntity.ok(gamesProcessingHelper.getAllGames());
    }
}
