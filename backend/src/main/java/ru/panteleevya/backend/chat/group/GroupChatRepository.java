package ru.panteleevya.backend.chat.group;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupChatRepository extends MongoRepository<GroupChatDocument, String> {
    Optional<GroupChatDocument> findByChatId(String chatId);
}
