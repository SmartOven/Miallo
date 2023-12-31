package ru.panteleevya.backend.login.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "person_web_tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonWebTokenDocument {
    @Id
    private String id;
    private String personId;
    private String token;
    private Long expiringAtMillis; // содержит в себе Instant.now().toEpochMilli()
}
