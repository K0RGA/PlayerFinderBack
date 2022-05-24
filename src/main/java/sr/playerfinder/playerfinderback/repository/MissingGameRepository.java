package sr.playerfinder.playerfinderback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sr.playerfinder.playerfinderback.dto.entity.MissingGame;

import java.util.Optional;

@Repository
public interface MissingGameRepository extends JpaRepository<MissingGame, Long> {
    Optional<MissingGame> findByName(String name);
}
