package sr.playerfinder.playerfinderback.dto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
@Table(name = "board_game", schema = "finder")
@EntityListeners(AuditingEntityListener.class)
public class BoardGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    @JsonIgnore
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "image", nullable = true, length = 1023)
    private String imageURL;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            schema = "finder",
            name = "game_link",
            joinColumns = {@JoinColumn(name = "board_game_id")},
            inverseJoinColumns = {@JoinColumn(name = "player_id")}
    )
    @JsonIgnore
    private Set<Player> players;

    @CreatedDate
    @Column(name = "created", nullable = false)
    @JsonIgnore
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "updated", nullable = false)
    @JsonIgnore
    private LocalDateTime updated;
}
