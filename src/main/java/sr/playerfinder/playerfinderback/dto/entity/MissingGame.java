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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
@Table(name = "missing_game", schema = "finder")
@EntityListeners(AuditingEntityListener.class)
public class MissingGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    @JsonIgnore
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "suggested_by", nullable = true)
    private String suggestedBy;

    @Column(name = "suggestion_count", nullable = true)
    private Long suggestionCount;

    @CreatedDate
    @Column(name = "created", nullable = false)
    @JsonIgnore
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "updated", nullable = false)
    @JsonIgnore
    private LocalDateTime updated;
}
