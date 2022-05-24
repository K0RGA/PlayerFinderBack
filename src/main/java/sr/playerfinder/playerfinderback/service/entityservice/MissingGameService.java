package sr.playerfinder.playerfinderback.service.entityservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sr.playerfinder.playerfinderback.dto.entity.MissingGame;
import sr.playerfinder.playerfinderback.repository.MissingGameRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class MissingGameService {

    private final MissingGameRepository missingGameRepository;

    public MissingGame get(Long id) {
        return missingGameRepository.findById(id).orElse(null);
    }

    public MissingGame findByName(String name) {
        return missingGameRepository.findByName(name).orElse(null);
    }

    public MissingGame save(MissingGame missingGame) {
        return missingGameRepository.save(missingGame);
    }
}
