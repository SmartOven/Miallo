package ru.panteleevya.chat;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final ChatIdProvider chatIdProvider;
    private final ChatValidator chatValidator;
    private final ChatMapper chatMapper;

    public ChatService(
            ChatRepository chatRepository,
            ChatIdProvider chatIdProvider,
            ChatValidator chatValidator,
            ChatMapper chatMapper
    ) {
        this.chatRepository = chatRepository;
        this.chatIdProvider = chatIdProvider;
        this.chatValidator = chatValidator;
        this.chatMapper = chatMapper;
    }

    public ChatDocument create(Chat chat) {
        ChatDocument chatDocument = chatMapper.toChatDocument(chat);
        chatDocument.setChatId(chatIdProvider.createChatId());
        chatValidator.validateCreate(chatDocument);
        return chatRepository.save(chatDocument);
    }

    public Optional<Chat> findByChatId(String chatId) {
        return chatRepository.findByChatId(chatId).map(chatMapper::toChat);
    }

    public Optional<Chat> findByTitle(String title) {
        return chatRepository.findByTitle(title).map(chatMapper::toChat);
    }
}