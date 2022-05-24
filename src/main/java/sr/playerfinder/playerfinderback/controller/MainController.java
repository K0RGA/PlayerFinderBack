package sr.playerfinder.playerfinderback.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sr.playerfinder.playerfinderback.dto.request.NewPlayerRq;
import sr.playerfinder.playerfinderback.service.helper.MainHelper;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class MainController {

    private final MainHelper mainHelper;

    @GetMapping("/getGames")
    public ResponseEntity<String> getAllGames() {
        log.info("Request: all games");
        return ResponseEntity.ok(mainHelper.getAllGames());
    }

    @PostMapping("/newUser")
    public ResponseEntity<String> registerNewUser(NewPlayerRq request) {
        log.info("New user requested, \nUsername: {}\nPassword: {}\nE-Mail: {}\nPhone: {}\nAge: {}\nGames: {}",
                request.getUsername(), request.getPassword(), request.getEmail(), request.getPhone(), request.getAge(),
                request.getGames().toString());
        return ResponseEntity.ok(mainHelper.processNewPlayer(request));
    }
}
