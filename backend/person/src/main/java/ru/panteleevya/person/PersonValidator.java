package ru.panteleevya.person;

import org.springframework.stereotype.Service;
import ru.panteleevya.alias.AliasRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonValidator {
    private final PersonRepository personRepository;
    private final AliasRepository aliasRepository;

    public PersonValidator(
            PersonRepository personRepository,
            AliasRepository aliasRepository
    ) {
        this.personRepository = personRepository;
        this.aliasRepository = aliasRepository;
    }

    public void validateCreate(Person person) {
        List<String> fields = new ArrayList<>();
        if (personRepository.existsByPersonId(person.getPersonId())) {
            fields.add("personId=" + person.getPersonId());
        }
        if (personRepository.existsByNickname(person.getNickname())) {
            fields.add("nickname=" + person.getNickname());
        }
        if (!fields.isEmpty()) {
            String errorMessage = String.format("Person with fields [%s] already exists", String.join(", ", fields));
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
