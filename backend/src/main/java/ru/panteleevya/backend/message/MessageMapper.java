package ru.panteleevya.backend.message;

import org.springframework.stereotype.Service;
import ru.panteleevya.backend.message.web.MessageDto;

import java.time.Instant;

@Service
public class MessageMapper {
    public MessageDocument toMessageEntity(MessageDto messageDto) {
        Long timestamp = Instant.now().toEpochMilli();
        return new MessageDocument(
                null,
                messageDto.getChatId(),
                messageDto.getAuthorPersonId(),
                messageDto.getText(),
                timestamp
        );
    }

    public Message toMessage(MessageDocument messageDocument) {
        return new Message(
                messageDocument.getChatId(),
                messageDocument.getAuthorPersonId(),
                messageDocument.getText(),
                messageDocument.getTimestamp()
        );
    }
}
