package ru.panteleevya.person;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PersonIdProvider {
    public String createPersonId() {
        return UUID.randomUUID().toString();
    }
}
