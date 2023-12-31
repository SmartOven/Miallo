package ru.panteleevya.backend.chat.personal;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonalChatRepository extends MongoRepository<PersonalChatDocument, String> {
    Optional<PersonalChatDocument> findByChatId(String chatId);
}
