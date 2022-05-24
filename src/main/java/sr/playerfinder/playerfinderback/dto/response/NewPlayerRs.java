package sr.playerfinder.playerfinderback.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewPlayerRs {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Europe/Moscow")
    private final LocalDateTime responseTime;

    private final String message;

    public NewPlayerRs(String message) {
        this.responseTime = LocalDateTime.now();
        this.message = message;
    }
}
