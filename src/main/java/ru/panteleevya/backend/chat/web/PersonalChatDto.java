package ru.panteleevya.backend.chat.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Для создания личного чата
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalChatDto {
    private String personId;
    private String otherPersonId;
}
