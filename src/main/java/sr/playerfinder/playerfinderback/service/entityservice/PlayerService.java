package sr.playerfinder.playerfinderback.service.entityservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sr.playerfinder.playerfinderback.dto.entity.Player;
import sr.playerfinder.playerfinderback.repository.PlayerRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    public Player get(Long id) {
        return playerRepository.findById(id).orElse(null);
    }

    public Player findByName(String name) {
        return playerRepository.findByUsername(name).orElse(null);
    }

    public Player save(Player newPlayer) {
        return playerRepository.save(newPlayer);
    }
}
