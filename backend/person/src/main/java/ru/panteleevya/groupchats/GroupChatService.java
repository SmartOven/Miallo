package ru.panteleevya.groupchats;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupChatService {
    private final GroupChatRepository groupChatRepository;
    private final GroupChatValidator groupChatValidator;
    private final GroupChatMapper groupChatMapper;

    public GroupChatService(
            GroupChatRepository groupChatRepository,
            GroupChatValidator groupChatValidator,
            GroupChatMapper groupChatMapper
    ) {
        this.groupChatRepository = groupChatRepository;
        this.groupChatValidator = groupChatValidator;
        this.groupChatMapper = groupChatMapper;
    }

    public void create(GroupChat groupChat) {
        groupChatValidator.validateCreate(groupChat);
        groupChatRepository.save(groupChatMapper.toGroupChatEntity(groupChat));
    }

    public List<GroupChat> findAllByOwnerPersonId(String personId) {
        return groupChatRepository.findAllByOwnerPersonId(personId).stream()
                .map(groupChatMapper::toGroupChat)
                .toList();
    }

    public Optional<GroupChat> findByTitle(String title) {
        return groupChatRepository.findByTitle(title).map(groupChatMapper::toGroupChat);
    }
}
