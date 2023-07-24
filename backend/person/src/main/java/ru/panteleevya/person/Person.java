package ru.panteleevya.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String personId;
    private String surname;
    private String name;
    private String nickname;
    private String bio;
}
