package ru.panteleevya.groupchats;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GroupChatValidator {
    private final GroupChatRepository groupChatRepository;

    public GroupChatValidator(GroupChatRepository groupChatRepository) {
        this.groupChatRepository = groupChatRepository;
    }

    public void validateCreate(GroupChat groupChat) {
        String title = groupChat.getTitle();
        String ownerPersonId = groupChat.getOwnerPersonId();
        Set<String> membersPersonIds = groupChat.getMembersPersonIds();
        var groupChatEntityOptional = groupChatRepository.findByTitleAndOwnerPersonId(title, ownerPersonId);
        if (groupChatEntityOptional.isEmpty()) {
            return;
        }
        GroupChatEntity groupChatEntity = groupChatEntityOptional.get();
        if (membersPersonIds.equals(groupChatEntity.getMembersPersonIds())) {
            throw new IllegalArgumentException(String.format(
                    "Group chat with title=%s, ownerPersonId=%s and same %s membersPersonIds already exists",
                    title,
                    ownerPersonId,
                    membersPersonIds.size()
            ));
        }
    }
}
