package ru.panteleevya.backend.person.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Для изменения информации о пользователе
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonInfo {
    private String surname;
    private String name;
    private String nickname;
    private String bio;
}
