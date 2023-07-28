package ru.panteleevya.person;

import org.springframework.stereotype.Service;
import ru.panteleevya.alias.PersonAlias;
import ru.panteleevya.alias.PersonAliasRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonValidator {
    private final PersonRepository personRepository;
    private final PersonAliasRepository personAliasRepository;

    public PersonValidator(
            PersonRepository personRepository,
            PersonAliasRepository personAliasRepository
    ) {
        this.personRepository = personRepository;
        this.personAliasRepository = personAliasRepository;
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

    public void validateAddAlias(PersonAlias personAlias) {
        String personId = personAlias.getPersonId();
        String aliasedPersonId = personAlias.getAliasedPersonId();
        if (!personRepository.existsByPersonId(personId)) {
            throw new IllegalArgumentException(String.format("Person with personId=%s doesn't exist", personId));
        }
        if (!personRepository.existsByPersonId(aliasedPersonId)) {
            throw new IllegalArgumentException(String.format("Person with personId=%s doesn't exist", aliasedPersonId));
        }
        if (personAliasRepository.existsByPersonIdAndAliasedPersonId(personId, aliasedPersonId)) {
            throw new IllegalArgumentException(String.format("Person with personId=%s doesn't exist", aliasedPersonId));
        }
    }
}
