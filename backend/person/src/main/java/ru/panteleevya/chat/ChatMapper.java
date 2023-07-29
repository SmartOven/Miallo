package ru.panteleevya.chat;

import org.springframework.stereotype.Service;

@Service
public class ChatMapper {
    public ChatDocument toChatDocument(Chat chat) {
        return new ChatDocument(
                null,
                chat.getTitle(),
                ChatType.valueOf(chat.getType()),
                chat.getChangedAt(),
                chat.getMembersPersonIds()
        );
    }

    public Chat toChat(ChatDocument chatDocument) {
        return new Chat(
                chatDocument.getChatId(),
                chatDocument.getTitle(),
                chatDocument.getType().name(),
                chatDocument.getChangedAt(),
                chatDocument.getMembersPersonIds()
        );
    }
}
