package ru.panteleevya.backend.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDocument {
    @Id
    private String id;
    private String chatId;
    private String authorPersonId;
    private String text;
    private Long timestamp;
}
