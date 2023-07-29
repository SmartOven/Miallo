package ru.panteleevya.backend.person.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.panteleevya.backend.person.Person;
import ru.panteleevya.backend.person.PersonDocument;
import ru.panteleevya.backend.person.PersonService;

import java.util.Optional;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
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
     * Обновление данных о пользователе: имя, фамилия, никнейм, био
     */
    @PutMapping("/updateInfo")
    public ResponseEntity<Person> updateInfo(@RequestParam String personId, @RequestBody PersonInfo personInfo) {
        return ResponseEntity.ok(personService.updateInfo(personId, personInfo));
    }

//    @GetMapping("/search")
//    public ResponseEntity<Person> search(@RequestParam String query) {
//        Optional<Person> personOptional = personService.findByNameOrSurnameOrNickname(query);
//        return personOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
}
