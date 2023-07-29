package ru.panteleevya.backend.person;

import org.springframework.stereotype.Service;
import ru.panteleevya.backend.person.web.PersonDto;
import ru.panteleevya.backend.person.web.PersonInfo;

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

    public PersonDocument create(PersonDto personDto) {
        PersonDocument personDocument = personMapper.toPersonDocument(personDto);
        personDocument.setPersonId(personIdProvider.createPersonId());
        personValidator.validateCreate(personDocument);
        return personRepository.save(personDocument);
    }

    public Optional<Person> findByPersonId(String personId) {
        return personRepository.findByPersonId(personId).map(personMapper::toPerson);
    }

    public Person getByPersonId(String personId) {
        return findByPersonId(personId).orElseThrow(() -> new IllegalArgumentException(String.format(
                "Person with personId=%s doesn't exist",
                personId
        )));
    }

    public Person updateInfo(String personId, PersonInfo personInfo) {
        personValidator.validateUpdate(personId, personInfo);
        PersonDocument personDocument = personRepository.findByPersonId(personId).get();
        personDocument.setSurname(personInfo.getSurname());
        personDocument.setName(personInfo.getName());
        personDocument.setNickname(personInfo.getNickname());
        personDocument.setBio(personInfo.getBio());
        return personMapper.toPerson(personRepository.save(personDocument));
    }

    public void addPersonalChat(String personId, PersonalChatInfo personalChatInfo) {
        PersonDocument personDocument = personRepository.findByPersonId(personId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Person with personId=%s doesn't exist", personId)));
        personDocument.getPersonalChats().add(personalChatInfo);
        personRepository.save(personDocument);
    }

    public void addGroupChat(String personId, GroupChatInfo groupChatInfo) {
        PersonDocument personDocument = personRepository.findByPersonId(personId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Person with personId=%s doesn't exist", personId)));
        personDocument.getGroupChats().add(groupChatInfo);
        personRepository.save(personDocument);
    }

//    public Optional<Person> findByNameOrSurnameOrNickname(String query) {
//        return personRepository.findByNameOrSurnameOrNickname(query, query, query).map(personMapper::toPerson);
//    }
}
