package ru.panteleevya.alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonAlias {
    private String personId;
    private String aliasedPersonId;
    private String aliasedSurname;
    private String aliasedName;
}
