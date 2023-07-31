package ru.panteleevya.backend.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String chatId;
    private String authorPersonId;
    private String text;
    private Long timestamp;
}
