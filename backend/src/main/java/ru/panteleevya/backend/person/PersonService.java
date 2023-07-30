package ru.panteleevya.backend.person;

import org.springframework.stereotype.Service;
import ru.panteleevya.backend.person.web.PersonDto;
import ru.panteleevya.backend.person.web.PersonInfo;

import java.util.List;
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

    public PersonDocument create(PersonDto personDto) {
        PersonDocument personDocument = personMapper.toPersonDocument(personDto);
        personValidator.validateCreate(personDocument);
        return personRepository.save(personDocument);
    }

    public Optional<Person> findByPersonId(String personId) {
        return personRepository.findByPersonId(personId).map(personMapper::toPerson);
    }

    public Optional<Person> findByNickname(String nickname) {
        return personRepository.findByNickname(nickname).map(personMapper::toPerson);
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

    public List<Person> findByQuery(String query) {
        return personRepository.findByQuery(query).stream().map(personMapper::toPerson).toList();
    }
}
