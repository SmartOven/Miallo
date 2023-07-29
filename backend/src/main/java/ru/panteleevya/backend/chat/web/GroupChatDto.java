package ru.panteleevya.backend.chat.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Для создания группового чата
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupChatDto {
    private String title;
    private List<String> membersPersonIds;
}
