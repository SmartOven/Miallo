package ru.panteleevya.backend.chat.personal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "personal_chats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalChatDocument {
    @Id
    private String chatId;
    private String personId;
    private String otherPersonId;
    private Long changedAt;
}
