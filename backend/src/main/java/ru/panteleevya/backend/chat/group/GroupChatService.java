package ru.panteleevya.backend.chat.group;

import org.springframework.stereotype.Service;
import ru.panteleevya.backend.chat.Chat;
import ru.panteleevya.backend.chat.ChatType;
import ru.panteleevya.backend.chat.web.GroupChatDto;
import ru.panteleevya.backend.person.GroupChatInfo;
import ru.panteleevya.backend.person.PersonService;

import java.time.Instant;
import java.util.Optional;

@Service
public class GroupChatService {
    private final PersonService personService;
    private final GroupChatRepository groupChatRepository;
    private final GroupChatIdProvider groupChatIdProvider;

    public GroupChatService(
            PersonService personService,
            GroupChatRepository groupChatRepository,
            GroupChatIdProvider groupChatIdProvider
    ) {
        this.personService = personService;
        this.groupChatRepository = groupChatRepository;
        this.groupChatIdProvider = groupChatIdProvider;
    }

    public GroupChatDocument create(GroupChatDto groupChatDto) {
        String chatId = groupChatIdProvider.createChatId();
        Long timestamp = Instant.now().toEpochMilli();
        GroupChatDocument groupChatDocument = new GroupChatDocument(
                chatId,
                groupChatDto.getTitle(),
                timestamp
        );
        for (String memberPersonId : groupChatDto.getMembersPersonIds()) {
            personService.addGroupChat(memberPersonId, new GroupChatInfo(chatId));
        }
        return groupChatRepository.save(groupChatDocument);
    }

    public Optional<GroupChatDocument> findByChatId(String chatId) {
        return groupChatRepository.findByChatId(chatId);
    }

    public Optional<Chat> loadAsChat(GroupChatInfo groupChatInfo) {
        return groupChatRepository.findByChatId(groupChatInfo.getChatId()).map(groupChatDocument -> new Chat(
                groupChatDocument.getChatId(),
                groupChatDocument.getTitle(),
                ChatType.GROUP,
                groupChatDocument.getChangedAt()
        ));
    }
}
