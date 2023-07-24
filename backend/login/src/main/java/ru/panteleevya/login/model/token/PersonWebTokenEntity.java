package ru.panteleevya.login.model.token;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "person_web_tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonWebTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "person_id", nullable = false, unique = true)
    private String personId;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    /**
     * Содержит в себе Instant.now().toEpochMilli()
     */
    @Column(name = "expiring_at_millis", nullable = false, unique = true)
    private Long expiringAtMillis;
}
