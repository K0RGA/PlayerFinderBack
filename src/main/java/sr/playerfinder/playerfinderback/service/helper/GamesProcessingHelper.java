package sr.playerfinder.playerfinderback.service.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sr.playerfinder.playerfinderback.dto.entity.BoardGame;
import sr.playerfinder.playerfinderback.dto.response.AllGamesRs;
import sr.playerfinder.playerfinderback.service.entityservice.BoardGameService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GamesProcessingHelper {

    private final BoardGameService boardGameService;
    private final ObjectMapper objectMapper;

    public String getAllGames() {

        try {
            List<BoardGame> gameList = boardGameService.getAll();
            return objectMapper.writeValueAsString(new AllGamesRs(gameList));
        }
        catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return "Internal server error";
        }
    }
}
