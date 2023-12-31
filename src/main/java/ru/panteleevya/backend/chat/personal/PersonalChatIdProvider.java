package ru.panteleevya.backend.chat.personal;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PersonalChatIdProvider {
    public String createChatId() {
        return UUID.randomUUID().toString();
    }
}
