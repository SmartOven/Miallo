package ru.panteleevya.backend.login.credentials;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonCredentials {
    private String login;
    private String password;
}
