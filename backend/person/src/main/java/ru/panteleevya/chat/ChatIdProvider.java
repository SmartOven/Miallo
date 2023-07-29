package ru.panteleevya.chat;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChatIdProvider {
    public String createChatId() {
        return UUID.randomUUID().toString();
    }
}
