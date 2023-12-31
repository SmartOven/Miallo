package ru.panteleevya.backend.chat.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "group_chats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupChatDocument {
    @Id
    private String chatId;
    private String title;
    private Long changedAt;
}
