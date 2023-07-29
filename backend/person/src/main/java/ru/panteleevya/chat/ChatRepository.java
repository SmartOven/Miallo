package ru.panteleevya.chat;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepository extends MongoRepository<ChatDocument, String> {
    boolean existsByChatId(String chatId);
    Optional<ChatDocument> findByChatId(String chatId);
    Optional<ChatDocument> findByTitle(String title);
}
