package ru.panteleevya.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.panteleevya.PersonChats;
import ru.panteleevya.groupchats.GroupChat;
import ru.panteleevya.groupchats.GroupChatService;
import ru.panteleevya.personalchats.PersonalChat;
import ru.panteleevya.personalchats.PersonalChatService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chats")
public class ChatsController {
    private final GroupChatService groupChatService;
    private final PersonalChatService personalChatService;

    public ChatsController(GroupChatService groupChatService, PersonalChatService personalChatService) {
        this.groupChatService = groupChatService;
        this.personalChatService = personalChatService;
    }

    @GetMapping("/list")
    public ResponseEntity<PersonChats> list(@RequestParam String personId) {
        List<PersonalChat> personalChats = personalChatService.findAllByOwnerPersonId(personId);
        List<GroupChat> groupChats = groupChatService.findAllByOwnerPersonId(personId);
        return ResponseEntity.ok(new PersonChats(personalChats, groupChats));
    }

    @PostMapping("/group-chat/create")
    public ResponseEntity<?> createGroupChat(@RequestBody GroupChat groupChat) {
        groupChatService.create(groupChat);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/group-chat/find")
    public ResponseEntity<GroupChat> findGroupChat(@RequestParam String title) {
        Optional<GroupChat> groupChatOptional = groupChatService.findByTitle(title);
        return groupChatOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/personal-chat/create")
    public ResponseEntity<?> createPersonalChat(@RequestBody PersonalChat personalChat) {
        personalChatService.create(personalChat);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/personal-chat/find")
    public ResponseEntity<PersonalChat> findPersonalChat(@RequestParam String title) {
        Optional<PersonalChat> personalChatOptional = personalChatService.findByTitle(title);
        return personalChatOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
