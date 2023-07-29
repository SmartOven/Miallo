package ru.panteleevya.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "persons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDocument {
    @Id
    private String personId;
    private String surname;
    private String name;
    private String nickname;
    private String bio;
    private Set<Alias> aliases;
    private Set<String> chatIds;
}
