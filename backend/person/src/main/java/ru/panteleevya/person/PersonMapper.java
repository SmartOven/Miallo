package ru.panteleevya.person;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class PersonMapper {
    public PersonDocument toPersonDocument(Person person) {
        Set<Alias> aliases = person.getAliases();
        Set<String> chatIds = person.getChatIds();
        return new PersonDocument(
                null,
                person.getSurname(),
                person.getName(),
                person.getNickname(),
                person.getBio(),
                aliases == null ? Collections.emptySet() : aliases,
                chatIds == null ? Collections.emptySet() : chatIds
        );
    }

    public Person toPerson(PersonDocument personDocument) {
        return new Person(
                personDocument.getPersonId(),
                personDocument.getSurname(),
                personDocument.getName(),
                personDocument.getNickname(),
                personDocument.getBio(),
                personDocument.getAliases(),
                personDocument.getChatIds()
        );
    }
}
