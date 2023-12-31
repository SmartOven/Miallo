package ru.panteleevya.backend.message;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<MessageDocument, String> {
    List<MessageDocument> findTop100ByChatIdOrderByTimestampDesc(String chatId);
}
