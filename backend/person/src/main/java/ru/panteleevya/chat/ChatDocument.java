package ru.panteleevya.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "chats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatDocument {
    @Id
    private String chatId;
    private String title;
    private ChatType type;
    private Long changedAt;
    private Set<String> membersPersonIds;
}
