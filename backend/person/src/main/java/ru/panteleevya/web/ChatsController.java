package ru.panteleevya.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.panteleevya.FindChatsRequest;
import ru.panteleevya.chat.Chat;
import ru.panteleevya.chat.ChatDocument;
import ru.panteleevya.chat.ChatService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chat")
public class ChatsController {
    private final ChatService chatService;

    public ChatsController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/create")
    public ResponseEntity<ChatDocument> create(@RequestParam String personId, @RequestBody Chat chat) {
        ChatDocument chatDocument = chatService.create(chat, personId);
        return ResponseEntity.ok(chatDocument);
    }

    @PostMapping("/find")
    public ResponseEntity<List<Chat>> find(@RequestBody FindChatsRequest request) {
        List<Chat> chats = request.getChatIds().stream()
                .map(chatService::findByChatId)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
        return ResponseEntity.ok(chats);
    }

//    @GetMapping("/search")
//    public ResponseEntity<Chat> search(@RequestParam String title) {
//        Optional<Chat> chatOptional = chatService.findByTitle(title);
//        return chatOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
}
