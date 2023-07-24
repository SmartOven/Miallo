package ru.panteleevya.groupchats;

import org.springframework.stereotype.Service;

@Service
public class GroupChatMapper {
    public GroupChatEntity toGroupChatEntity(GroupChat groupChat) {
        return new GroupChatEntity(
                null,
                groupChat.getTitle(),
                groupChat.getOwnerPersonId(),
                groupChat.getMembersPersonIds()
        );
    }

    public GroupChat toGroupChat(GroupChatEntity groupChatEntity) {
        return new GroupChat(
                groupChatEntity.getTitle(),
                groupChatEntity.getOwnerPersonId(),
                groupChatEntity.getMembersPersonIds()
        );
    }
}
