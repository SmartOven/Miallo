package ru.panteleevya.chat;

import org.springframework.stereotype.Service;
import ru.panteleevya.person.PersonDocument;
import ru.panteleevya.person.PersonRepository;

import java.time.Instant;
import java.util.Optional;

@Service
public class ChatService {
    private final PersonRepository personRepository;
    private final ChatRepository chatRepository;
    private final ChatIdProvider chatIdProvider;
    private final ChatValidator chatValidator;
    private final ChatMapper chatMapper;

    public ChatService(
            PersonRepository personRepository,
            ChatRepository chatRepository,
            ChatIdProvider chatIdProvider,
            ChatValidator chatValidator,
            ChatMapper chatMapper
    ) {
        this.personRepository = personRepository;
        this.chatRepository = chatRepository;
        this.chatIdProvider = chatIdProvider;
        this.chatValidator = chatValidator;
        this.chatMapper = chatMapper;
    }

    public ChatDocument create(Chat chat, String personId) {
        Long changedAt = Instant.now().toEpochMilli();
        String chatTypeString = chat.getType().toUpperCase();
        ChatType chatType;
        try {
            chatType = ChatType.valueOf(chatTypeString);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Chat with type=%s doesn't exist", chatTypeString));
        }
        String title = getChatTitle(chat, chatType, personId);
        ChatDocument chatDocument = new ChatDocument(
                chatIdProvider.createChatId(),
                title,
                chatType,
                changedAt,
                chat.getMembersPersonIds()
        );
        chatValidator.validateCreate(chatDocument);
        return chatRepository.save(chatDocument);
    }

    public Optional<Chat> findByChatId(String chatId) {
        return chatRepository.findByChatId(chatId).map(chatMapper::toChat);
    }

    public Optional<Chat> findByTitle(String title) {
        return chatRepository.findByTitle(title).map(chatMapper::toChat);
    }

    private String getChatTitle(Chat chat, ChatType chatType, String personId) {
        return switch (chatType) {
            case PERSONAL -> getPersonalChatTitle(personId);
            case GROUP -> chat.getTitle();
        };
    }

    private String getPersonalChatTitle(String personId) {
        PersonDocument personDocument = personRepository.findByPersonId(personId).orElseThrow(() -> new RuntimeException(String.format(
                "Validator didn't check person with personId=%s doesn't exist",
                personId
        )));
        return String.join(" ", personDocument.getName(), personDocument.getName());
    }
}