package ru.panteleevya.backend.person.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Для создания пользователя при регистрации
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    private String surname = "";
    private String name;
    private String nickname;
    private String bio = "";
}
