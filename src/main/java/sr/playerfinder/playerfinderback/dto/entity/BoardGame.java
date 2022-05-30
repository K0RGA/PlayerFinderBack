package sr.playerfinder.playerfinderback.dto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import java.util.HashSet;
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

    @Column(name = "percentage")
    private Float percentage;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            schema = "finder",
            name = "game_link",
            joinColumns = @JoinColumn(name = "board_game_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"board_game_id", "player_id"})
    )
    @JsonIgnore
    private Set<Player> players = new HashSet<>();

    @CreatedDate
    @Column(name = "created", nullable = false)
    @JsonIgnore
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "updated", nullable = false)
    @JsonIgnore
    private LocalDateTime updated;
}
