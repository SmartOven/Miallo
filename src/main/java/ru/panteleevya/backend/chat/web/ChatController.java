package ru.panteleevya.backend.chat.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.panteleevya.backend.chat.Chat;
import ru.panteleevya.backend.chat.group.GroupChatService;
import ru.panteleevya.backend.chat.personal.PersonalChatService;
import ru.panteleevya.backend.person.Person;
import ru.panteleevya.backend.person.PersonService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private final PersonService personService;
    private final GroupChatService groupChatService;
    private final PersonalChatService personalChatService;

    public ChatController(
            PersonService personService,
            GroupChatService groupChatService,
            PersonalChatService personalChatService
    ) {
        this.personService = personService;
        this.groupChatService = groupChatService;
        this.personalChatService = personalChatService;
    }

    @PostMapping("/group/create")
    public ResponseEntity<?> createGroupChat(@RequestBody GroupChatDto groupChatDto) {
        return ResponseEntity.ok(groupChatService.create(groupChatDto));
    }

    @PostMapping("/personal/create")
    public ResponseEntity<?> createPersonalChat(@RequestBody PersonalChatDto personalChatDto) {
        return ResponseEntity.ok(personalChatService.create(personalChatDto));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Chat>> findAllChats(@RequestParam String personId) {
        Person person = personService.getByPersonId(personId);
        List<Chat> groupChats = person.getGroupChats().stream()
                .map(groupChatService::loadAsChat)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
        List<Chat> personalChats = person.getPersonalChats().stream()
                .map(personalChatInfo -> personalChatService.loadAsChatByChatId(personalChatInfo, personId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
        List<Chat> chats = new ArrayList<>(groupChats.size() + personalChats.size());
        chats.addAll(groupChats);
        chats.addAll(personalChats);
        return ResponseEntity.ok(chats);
    }
}
