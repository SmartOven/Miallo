package ru.panteleevya.backend.login.credentials;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "person_credentials")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonCredentialsDocument {
    @Id
    private String id;
    private String personId;
    private String login;
    private String password;
}
