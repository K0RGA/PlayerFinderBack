package sr.playerfinder.playerfinderback.service.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import sr.playerfinder.playerfinderback.dto.entity.BoardGame;
import sr.playerfinder.playerfinderback.dto.entity.MissingGame;
import sr.playerfinder.playerfinderback.dto.entity.Player;
import sr.playerfinder.playerfinderback.dto.request.NewPlayerRq;
import sr.playerfinder.playerfinderback.dto.response.NewPlayerRs;
import sr.playerfinder.playerfinderback.service.entityservice.BoardGameService;
import sr.playerfinder.playerfinderback.service.entityservice.MissingGameService;
import sr.playerfinder.playerfinderback.service.entityservice.PlayerService;

import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerRegistrationHelper {

    private final BoardGameService boardGameService;
    private final PlayerService playerService;
    private final MissingGameService missingGameService;

    private final ObjectMapper objectMapper;

    public String processNewPlayer(NewPlayerRq request) {

        Player newPlayer = Player.builder()
                .age(request.getAge())
                .email(request.getEmail())
                .phone(request.getPhone())
                .username(request.getUsername())
                .password(request.getPassword())
                .rating(4.0F)
                .build();

        List<BoardGame> fetchedGames = boardGameService.findAllInList(request.getGames());
        processMissingGames(request.getGames(), fetchedGames, request.getUsername());

        newPlayer.setBoardGames(new HashSet<>(fetchedGames));
        try {
            playerService.save(newPlayer);
            for (BoardGame game : fetchedGames) {
                game.getPlayers().add(newPlayer);
                boardGameService.save(game);
            }
            NewPlayerRs response = new NewPlayerRs("Успешная регистрация");
            return objectMapper.writeValueAsString(response);
        }
        catch (HibernateException | JsonProcessingException e) {
            log.error(e.getMessage());
            NewPlayerRs response = new NewPlayerRs("Не повезло, не фартануло");
            try {
                return objectMapper.writeValueAsString(response);
            }
            catch (JsonProcessingException ex) {
                log.error(ex.getMessage());
                return "Internal server error";
            }
        }
    }

    private void processMissingGames(List<String> games, List<BoardGame> actualGames, String playerName) {

        games.removeIf(name -> actualGames.stream().map(BoardGame::getName).toList().contains(name));
        for (String gameName : games) {
            MissingGame curGame = missingGameService.findByName(gameName);
            if (curGame == null) {
                missingGameService.save(MissingGame.builder()
                                .name(gameName)
                                .suggestedBy(playerName)
                                .suggestionCount(1L)
                                .build());
            }
            else {
                curGame.setSuggestionCount(curGame.getSuggestionCount() + 1);
                missingGameService.save(curGame);
            }
        }
    }
}
