package ru.panteleevya.backend.message;

import org.springframework.stereotype.Service;
import ru.panteleevya.backend.message.web.MessageDto;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    public MessageService(MessageRepository messageRepository, MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }

    public Message create(MessageDto messageDto) {
        MessageDocument messageDocument = messageMapper.toMessageEntity(messageDto);
        MessageDocument savedMessageDocument = messageRepository.save(messageDocument);
        return messageMapper.toMessage(savedMessageDocument);
    }

    public List<Message> findLast100MessagesByChatId(String chatId) {
        return messageRepository.findTop100ByChatIdOrderByTimestampDesc(chatId).stream().map(messageMapper::toMessage).toList();
    }
}
