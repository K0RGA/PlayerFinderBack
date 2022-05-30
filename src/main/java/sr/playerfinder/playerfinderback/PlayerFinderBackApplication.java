package sr.playerfinder.playerfinderback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PlayerFinderBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayerFinderBackApplication.class, args);
    }
}
