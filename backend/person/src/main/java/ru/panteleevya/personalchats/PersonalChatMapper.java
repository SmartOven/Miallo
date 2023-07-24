package ru.panteleevya.personalchats;

import org.springframework.stereotype.Service;

@Service
public class PersonalChatMapper {
    public PersonalChatEntity toPersonalChatEntity(PersonalChat personalChat) {
        return new PersonalChatEntity(
                null,
                personalChat.getTitle(),
                personalChat.getOwnerPersonId(),
                personalChat.getParticipantPersonId()
        );
    }

    public PersonalChat toPersonalChat(PersonalChatEntity personalChatEntity) {
        return new PersonalChat(
                personalChatEntity.getTitle(),
                personalChatEntity.getOwnerPersonId(),
                personalChatEntity.getParticipantPersonId()
        );
    }
}
