package ru.panteleevya.personalchats;

import org.springframework.stereotype.Service;

@Service
public class PersonalChatValidator {
    private final PersonalChatRepository personalChatRepository;

    public PersonalChatValidator(PersonalChatRepository personalChatRepository) {
        this.personalChatRepository = personalChatRepository;
    }

    public void validateCreate(PersonalChat personalChat) {
        String ownerPersonId = personalChat.getOwnerPersonId();
        String participantPersonId = personalChat.getParticipantPersonId();
        if (personalChatRepository.existsByOwnerPersonIdAndParticipantPersonId(ownerPersonId, participantPersonId)) {
            throw new IllegalArgumentException(String.format(
                    "Personal chat between ownerPersonId=%s and participantPersonId=%s already exists",
                    ownerPersonId,
                    participantPersonId
            ));
        }
    }
}
