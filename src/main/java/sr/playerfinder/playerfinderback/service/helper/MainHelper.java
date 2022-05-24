package sr.playerfinder.playerfinderback.service.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import sr.playerfinder.playerfinderback.dto.entity.BoardGame;
import sr.playerfinder.playerfinderback.dto.entity.Player;
import sr.playerfinder.playerfinderback.dto.request.NewPlayerRq;
import sr.playerfinder.playerfinderback.dto.response.AllGamesRs;
import sr.playerfinder.playerfinderback.dto.response.NewPlayerRs;
import sr.playerfinder.playerfinderback.service.entityservice.BoardGameService;
import sr.playerfinder.playerfinderback.service.entityservice.PlayerService;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class MainHelper {

    private final BoardGameService boardGameService;
    private final PlayerService playerService;
    private final ObjectMapper objectMapper;

    public String getAllGames() {

        String result;
        try {
            List<BoardGame> gameList = boardGameService.getAll();
            result = objectMapper.writeValueAsString(new AllGamesRs(gameList));
            return result;
        }
        catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return "Internal server error";
        }
    }

    public String processNewPlayer(NewPlayerRq request) {

        Player newPlayer = Player.builder()
                .age(request.getAge())
                .email(request.getEmail())
                .phone(request.getPhone())
                .username(request.getUsername())
                .password(request.getPassword())
                .rating(4.0f)
                .build();

        Set<BoardGame> fetchedGames = boardGameService.findAllInList(request.getGames());
        newPlayer.setBoardGames(fetchedGames);
        try {
            playerService.save(newPlayer);
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
}
