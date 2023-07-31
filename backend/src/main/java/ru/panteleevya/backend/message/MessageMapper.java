package ru.panteleevya.backend.message;

import org.springframework.stereotype.Service;
import ru.panteleevya.backend.message.web.MessageDto;

import java.time.Instant;

@Service
public class MessageMapper {
    public MessageEntity toMessageEntity(MessageDto messageDto) {
        Long timestamp = Instant.now().toEpochMilli();
        return new MessageEntity(
                null,
                messageDto.getChatId(),
                messageDto.getAuthorPersonId(),
                messageDto.getText(),
                timestamp
        );
    }

    public Message toMessage(MessageEntity messageEntity) {
        return new Message(
                messageEntity.getChatId(),
                messageEntity.getAuthorPersonId(),
                messageEntity.getText(),
                messageEntity.getTimestamp()
        );
    }
}
