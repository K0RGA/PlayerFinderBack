package sr.playerfinder.playerfinderback.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import sr.playerfinder.playerfinderback.dto.entity.BoardGame;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AllGamesRs {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Europe/Moscow")
    private final LocalDateTime responseTime;

    private final List<BoardGame> availableGames;

    public AllGamesRs(List<BoardGame> games) {
        this.availableGames = games;
        this.responseTime = LocalDateTime.now();
    }
}
