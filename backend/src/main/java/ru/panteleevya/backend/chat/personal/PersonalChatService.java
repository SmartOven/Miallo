package ru.panteleevya.backend.chat.personal;

import org.springframework.stereotype.Service;
import ru.panteleevya.backend.chat.Chat;
import ru.panteleevya.backend.chat.ChatType;
import ru.panteleevya.backend.chat.web.PersonalChatDto;
import ru.panteleevya.backend.person.*;

import java.time.Instant;
import java.util.Optional;

@Service
public class PersonalChatService {
    private final PersonalChatRepository personalChatRepository;
    private final PersonService personService;
    private final PersonalChatIdProvider personalChatIdProvider;

    public PersonalChatService(
            PersonalChatRepository personalChatRepository,
            PersonService personService,
            PersonalChatIdProvider personalChatIdProvider
    ) {
        this.personalChatRepository = personalChatRepository;
        this.personService = personService;
        this.personalChatIdProvider = personalChatIdProvider;
    }

    public PersonalChatDocument create(PersonalChatDto personalChatDto) {
        String chatId = personalChatIdProvider.createChatId();
        Long timestamp = Instant.now().toEpochMilli();
        PersonalChatDocument personalChatDocument = new PersonalChatDocument(
                chatId,
                personalChatDto.getPersonId(),
                personalChatDto.getOtherPersonId(),
                timestamp
        );
        String personId = personalChatDto.getPersonId();
        String otherPersonId = personalChatDto.getOtherPersonId();
        personService.addPersonalChat(personId, new PersonalChatInfo(otherPersonId, chatId));
        personService.addPersonalChat(otherPersonId, new PersonalChatInfo(personId, chatId));
        return personalChatRepository.save(personalChatDocument);
    }

    public Optional<PersonalChatDocument> findByChatId(String chatId) {
        return personalChatRepository.findByChatId(chatId);
    }

    public Optional<Chat> loadAsChatByChatId(PersonalChatInfo personalChatInfo, String personId) {
        return personalChatRepository.findByChatId(personalChatInfo.getChatId()).map(personalChatDocument -> new Chat(
                personalChatDocument.getChatId(),
                getPersonalChatTitle(personalChatInfo, personId),
                ChatType.PERSONAL,
                personalChatDocument.getChangedAt()
        ));
    }

    private String getPersonalChatTitle(PersonalChatInfo personalChatInfo, String personId) {
        Person person = personService.getByPersonId(personId);
        String otherPersonId = personalChatInfo.getPersonId();
        Optional<Alias> optionalAlias = person.getAliases().stream()
                .filter(alias -> alias.getAliasedPersonId().equals(otherPersonId))
                .findFirst();
        if (optionalAlias.isEmpty()) {
            Person otherPerson = personService.getByPersonId(otherPersonId);
            return otherPerson.getName() + " " + otherPerson.getSurname();
        }
        Alias alias = optionalAlias.get();
        return alias.getAliasedName() + " " + alias.getAliasedSurname();
    }
}
