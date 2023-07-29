package ru.panteleevya.person;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonValidator personValidator;
    private final PersonMapper personMapper;
    private final PersonIdProvider personIdProvider;

    public PersonService(
            PersonRepository personRepository,
            PersonValidator personValidator,
            PersonMapper personMapper,
            PersonIdProvider personIdProvider
    ) {
        this.personRepository = personRepository;
        this.personValidator = personValidator;
        this.personMapper = personMapper;
        this.personIdProvider = personIdProvider;
    }

    public PersonDocument create(Person person) {
        PersonDocument personDocument = personMapper.toPersonDocument(person);
        personDocument.setPersonId(personIdProvider.createPersonId());
        personValidator.validateCreate(personDocument);
        return personRepository.save(personDocument);
    }

    public Optional<Person> findByPersonId(String personId) {
        return personRepository.findByPersonId(personId).map(personMapper::toPerson);
    }

    public Optional<Person> findByNameOrSurnameOrNickname(String query) {
        return personRepository.findByNameOrSurnameOrNickname(query, query, query).map(personMapper::toPerson);
    }
}
