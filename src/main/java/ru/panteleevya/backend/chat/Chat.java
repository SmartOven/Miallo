package ru.panteleevya.backend.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    private String chatId;
    private String title;
    private ChatType type;
    private Long changedAt;
}
