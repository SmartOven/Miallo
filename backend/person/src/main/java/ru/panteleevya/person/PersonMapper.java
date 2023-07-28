package ru.panteleevya.person;

import org.springframework.stereotype.Service;
import ru.panteleevya.alias.PersonAlias;
import ru.panteleevya.alias.PersonAliasEntity;

import java.util.Collections;

@Service
public class PersonMapper {
    public PersonEntity toPersonEntity(Person person) {
        return new PersonEntity(
                null,
                person.getPersonId(),
                person.getSurname(),
                person.getName(),
                person.getNickname(),
                person.getBio(),
                Collections.emptyList()
        );
    }

    public Person toPerson(PersonEntity personEntity) {
        return new Person(
                personEntity.getPersonId(),
                personEntity.getSurname(),
                personEntity.getName(),
                personEntity.getNickname(),
                personEntity.getBio(),
                personEntity.getAliases().stream().map(this::toPersonAlias).toList()
        );
    }

    public PersonAliasEntity toPersonAliasEntity(PersonAlias personAlias) {
        return new PersonAliasEntity(
                null,
                personAlias.getPersonId(),
                personAlias.getAliasedPersonId(),
                personAlias.getAliasedSurname(),
                personAlias.getAliasedName()
        );
    }

    public PersonAlias toPersonAlias(PersonAliasEntity personAliasEntity) {
        return new PersonAlias(
                personAliasEntity.getPersonId(),
                personAliasEntity.getAliasedPersonId(),
                personAliasEntity.getAliasedSurname(),
                personAliasEntity.getAliasedName()
        );
    }
}
