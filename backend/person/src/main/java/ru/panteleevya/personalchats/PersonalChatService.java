package ru.panteleevya.personalchats;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalChatService {
    private final PersonalChatRepository personalChatRepository;
    private final PersonalChatValidator personalChatValidator;
    private final PersonalChatMapper personalChatMapper;

    public PersonalChatService(
            PersonalChatRepository personalChatRepository,
            PersonalChatValidator personalChatValidator,
            PersonalChatMapper personalChatMapper
    ) {
        this.personalChatRepository = personalChatRepository;
        this.personalChatValidator = personalChatValidator;
        this.personalChatMapper = personalChatMapper;
    }

    public void create(PersonalChat personalChat) {
        personalChatValidator.validateCreate(personalChat);
        personalChatRepository.save(personalChatMapper.toPersonalChatEntity(personalChat));
    }

    public List<PersonalChat> findAllByOwnerPersonId(String personId) {
        return personalChatRepository.findAllByOwnerPersonId(personId).stream()
                .map(personalChatMapper::toPersonalChat)
                .toList();
    }

    public Optional<PersonalChat> findByTitle(String title) {
        return personalChatRepository.findByTitle(title).map(personalChatMapper::toPersonalChat);
    }
}
