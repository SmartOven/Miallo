package ru.panteleevya.backend.person.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.panteleevya.backend.chat.personal.PersonalChatDocument;
import ru.panteleevya.backend.chat.personal.PersonalChatService;
import ru.panteleevya.backend.person.Person;
import ru.panteleevya.backend.person.PersonDocument;
import ru.panteleevya.backend.person.PersonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    private final PersonService personService;
    private final PersonalChatService personalChatService;

    public PersonController(PersonService personService, PersonalChatService personalChatService) {
        this.personService = personService;
        this.personalChatService = personalChatService;
    }

    /**
     * Создание пользователя при регистрации
     */
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PersonDto personDto) {
        PersonDocument personDocument = personService.create(personDto);
        return ResponseEntity.ok(personDocument);
    }

    /**
     * Поиск данных о пользователе при авторизации в мессенджере
     */
    @GetMapping("/find")
    public ResponseEntity<Person> find(@RequestParam String personId) {
        Optional<Person> personOptional = personService.findByPersonId(personId);
        return personOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Поиск данных о пользователе при открытии чата с ним
     */
    @GetMapping("/findByNickname")
    public ResponseEntity<Person> findByNickname(@RequestParam String nickname) {
        Optional<Person> personOptional = personService.findByNickname(nickname);
        return personOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Поиск данных о пользователе с использованием chatId и personId
     */
    @GetMapping("/findByChat")
    public ResponseEntity<Person> findByChat(@RequestParam String chatId, @RequestParam String otherPersonId) {
        Optional<PersonalChatDocument> personalChatDocumentOptional = personalChatService.findByChatId(chatId);
        if (personalChatDocumentOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        PersonalChatDocument personalChatDocument = personalChatDocumentOptional.get();
        String personId = otherPersonId.equals(personalChatDocument.getOtherPersonId())
                ? personalChatDocument.getPersonId()
                : personalChatDocument.getOtherPersonId();
        Optional<Person> personOptional = personService.findByPersonId(personId);
        return personOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Обновление данных о пользователе: имя, фамилия, никнейм, био
     */
    @PutMapping("/updateInfo")
    public ResponseEntity<Person> updateInfo(@RequestParam String personId, @RequestBody PersonInfo personInfo) {
        return ResponseEntity.ok(personService.updateInfo(personId, personInfo));
    }

    /**
     * Поиск пользователей по их name/surname/nickname
     */
    @GetMapping("/search")
    public ResponseEntity<List<Person>> search(@RequestParam String query) {
        return ResponseEntity.ok(personService.findByQuery(query));
    }
}
