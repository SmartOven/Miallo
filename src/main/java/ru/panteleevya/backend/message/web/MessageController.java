package ru.panteleevya.backend.message.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.panteleevya.backend.message.Message;
import ru.panteleevya.backend.message.MessageService;

import java.util.List;

@RestController
@RequestMapping("/api/message/")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/create")
    public ResponseEntity<Message> create(@RequestBody MessageDto messageDto) {
        return ResponseEntity.ok(messageService.create(messageDto));
    }

    @GetMapping("/findLast100")
    public ResponseEntity<List<Message>> findLast100(@RequestParam String chatId) {
        return ResponseEntity.ok(messageService.findLast100MessagesByChatId(chatId));
    }
}
