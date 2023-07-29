package ru.panteleevya.chat;

import org.springframework.stereotype.Service;

@Service
public class ChatMapper {
    public Chat toChat(ChatDocument chatDocument) {
        return new Chat(
                chatDocument.getChatId(),
                chatDocument.getTitle(),
                chatDocument.getType().name().toLowerCase(),
                chatDocument.getChangedAt(),
                chatDocument.getMembersPersonIds()
        );
    }
}
