package ru.panteleevya.person;

import org.springframework.stereotype.Service;
import ru.panteleevya.alias.PersonAlias;
import ru.panteleevya.alias.PersonAliasEntity;
import ru.panteleevya.alias.PersonAliasRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonAliasRepository personAliasRepository;
    private final PersonValidator personValidator;
    private final PersonMapper personMapper;

    public PersonService(
            PersonRepository personRepository,
            PersonAliasRepository personAliasRepository,
            PersonValidator personValidator,
            PersonMapper personMapper
    ) {
        this.personRepository = personRepository;
        this.personAliasRepository = personAliasRepository;
        this.personValidator = personValidator;
        this.personMapper = personMapper;
    }

    public void create(Person person) {
        personValidator.validateCreate(person);
        personRepository.save(personMapper.toPersonEntity(person));
    }

    public Optional<Person> findByPersonId(String personId) {
        return personRepository.findByPersonId(personId).map(personMapper::toPerson);
    }

    public Optional<Person> findByNameOrSurnameOrNickname(String query) {
        return personRepository.findByNameOrSurnameOrNickname(query, query, query).map(personMapper::toPerson);
    }

    public void addAlias(PersonAlias personAlias) {
        personValidator.validateAddAlias(personAlias);
        String personId = personAlias.getPersonId();
        PersonEntity personEntity = personRepository.findByPersonId(personId)
                .orElseThrow(() -> new RuntimeException("Validator didn't check that person existence"));
        PersonAliasEntity personAliasEntity = personMapper.toPersonAliasEntity(personAlias);
        personAliasRepository.save(personAliasEntity);
        personEntity.getAliases().add(personAliasEntity);
        personRepository.save(personEntity);
    }
}
