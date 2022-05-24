package sr.playerfinder.playerfinderback.service.entityservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sr.playerfinder.playerfinderback.dto.entity.BoardGame;
import sr.playerfinder.playerfinderback.repository.BoardGameRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardGameService {

    private final BoardGameRepository boardGameRepository;

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
}
