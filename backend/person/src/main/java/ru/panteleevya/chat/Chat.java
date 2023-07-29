package ru.panteleevya.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    private String chatId;
    private String title;
    private String type;
    private Long changedAt;
    private Set<String> membersPersonIds;
}
