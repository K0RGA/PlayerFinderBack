package sr.playerfinder.playerfinderback.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class NewPlayerRq {

    private final String username;
    private final String password;
    private final String email;
    private final String phone;
    private final Long age;
    private final List<String> games;
}
