package ru.panteleevya.backend.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alias {
    private String aliasedPersonId;
    private String aliasedSurname;
    private String aliasedName;
}
