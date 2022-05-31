package sr.playerfinder.playerfinderback.service.entityservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sr.playerfinder.playerfinderback.dto.entity.BoardGame;
import sr.playerfinder.playerfinderback.repository.BoardGameRepository;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardGameService {

    private final BoardGameRepository boardGameRepository;
    private final PlayerService playerService;

    public BoardGame get(Long id) {
        return boardGameRepository.findById(id).orElse(null);
    }

    public List<BoardGame> getAll() {
        return boardGameRepository.findAll();
    }

    public BoardGame findByName(String name) {
        return boardGameRepository.findByName(name).orElse(null);
    }

    public List<BoardGame> findAllInList(List<String> names) {
        return boardGameRepository.findAllByNameIn(names);
    }

    public BoardGame save(BoardGame newGame) {
        return boardGameRepository.save(newGame);
    }

    @Scheduled(initialDelay = 1, fixedDelay = 30, timeUnit = TimeUnit.MINUTES)
    private void refreshPercentage() {
        log.info("Recalculating percentage...");
        for (BoardGame game : boardGameRepository.findAll()) {
            game.setPercentage((float) (100 * game.getPlayers().size()) / playerService.countAll());
            boardGameRepository.save(game);
        }
    }
}
