package ru.panteleevya.person;

import org.springframework.stereotype.Service;

@Service
public class PersonMapper {
    public PersonEntity toPersonEntity(Person person) {
        return new PersonEntity(
                null,
                person.getPersonId(),
                person.getSurname(),
                person.getName(),
                person.getNickname(),
                person.getBio()
        );
    }

    public Person toPerson(PersonEntity personEntity) {
        return new Person(
                personEntity.getPersonId(),
                personEntity.getSurname(),
                personEntity.getName(),
                personEntity.getNickname(),
                personEntity.getBio()
        );
    }
}
