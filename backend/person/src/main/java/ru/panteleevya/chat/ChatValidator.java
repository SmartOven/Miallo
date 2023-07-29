package ru.panteleevya.chat;

import org.springframework.stereotype.Service;
import ru.panteleevya.NonNullValidator;
import ru.panteleevya.person.PersonRepository;

import java.util.Map;
import java.util.function.Supplier;

@Service
public class ChatValidator {
    private final ChatRepository chatRepository;
    private final PersonRepository personRepository;
    private final NonNullValidator nonNullValidator;

    public ChatValidator(
            ChatRepository chatRepository,
            PersonRepository personRepository,
            NonNullValidator nonNullValidator
    ) {
        this.chatRepository = chatRepository;
        this.personRepository = personRepository;
        this.nonNullValidator = nonNullValidator;
    }

    public void validateCreate(ChatDocument chatDocument) {
        checkFieldsNotNull(chatDocument);
        String chatId = chatDocument.getChatId();
        if (chatRepository.existsByChatId(chatId)) {
            throw new IllegalArgumentException(String.format("Chat with chatId=%s already exists", chatId));
        }
        for (String memberPersonId : chatDocument.getMembersPersonIds()) {
            if (!personRepository.existsByPersonId(memberPersonId)) {
                throw new IllegalArgumentException(String.format("Person with personId=%s doesn't exist", memberPersonId));
            }
        }
    }

    private void checkFieldsNotNull(ChatDocument chatDocument) {
        Map<Supplier<Object>, String> map = Map.of(
                chatDocument::getChatId, "chatId",
                chatDocument::getTitle, "title",
                chatDocument::getType, "type",
                chatDocument::getChangedAt, "changedAt"
        );
        nonNullValidator.validateNonNullFields(map);
    }
}
