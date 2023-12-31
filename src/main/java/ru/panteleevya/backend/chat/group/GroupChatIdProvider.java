package ru.panteleevya.backend.chat.group;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GroupChatIdProvider {
    public String createChatId() {
        return UUID.randomUUID().toString();
    }
}
