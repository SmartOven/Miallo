package ru.panteleevya.backend.message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    @Query(value = "SELECT * FROM messages m WHERE m.chat_id = :chatId ORDER BY m.timestamp DESC LIMIT 100", nativeQuery = true)
    List<MessageEntity> findLast100MessagesByChatId(String chatId);
}
