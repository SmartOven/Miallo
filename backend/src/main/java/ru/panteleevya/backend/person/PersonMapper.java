package ru.panteleevya.backend.person;

import org.springframework.stereotype.Service;
import ru.panteleevya.backend.person.web.PersonDto;

import java.util.Collections;

@Service
public class PersonMapper {
    public PersonDocument toPersonDocument(PersonDto personDto) {
        return new PersonDocument(
                null,
                personDto.getSurname(),
                personDto.getName(),
                personDto.getNickname(),
                personDto.getBio(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
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
                personDocument.getPersonalChats(),
                personDocument.getGroupChats()
        );
    }
}
