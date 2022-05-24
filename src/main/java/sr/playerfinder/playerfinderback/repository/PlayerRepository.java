package sr.playerfinder.playerfinderback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sr.playerfinder.playerfinderback.dto.entity.Player;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByUsername(String username);
    List<Player> findAllByRatingBetween(Float lowBorder, Float highBorder);
}
