package ru.panteleevya.person;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonValidator personValidator;
    private final PersonMapper personMapper;

    public PersonService(
            PersonRepository personRepository,
            PersonValidator personValidator,
            PersonMapper personMapper
    ) {
        this.personRepository = personRepository;
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
}
