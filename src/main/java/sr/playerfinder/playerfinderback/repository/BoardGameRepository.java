package sr.playerfinder.playerfinderback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sr.playerfinder.playerfinderback.dto.entity.BoardGame;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BoardGameRepository extends JpaRepository<BoardGame, Long> {
    Optional<BoardGame> findByName(String name);
    Set<BoardGame> findAllByNameIn(List<String> names);
}
