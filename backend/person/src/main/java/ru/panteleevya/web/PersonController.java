package ru.panteleevya.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.panteleevya.person.Person;
import ru.panteleevya.person.PersonService;

import java.util.Optional;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Person person) {
        personService.create(person);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/info")
    public ResponseEntity<Person> info(@RequestParam String personId) {
        Optional<Person> personOptional = personService.findByPersonId(personId);
        return personOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/find")
    public ResponseEntity<Person> find(@RequestParam String query) {
        Optional<Person> personOptional = personService.findByNameOrSurnameOrNickname(query);
        return personOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
