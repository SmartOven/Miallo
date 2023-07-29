package ru.panteleevya.backend.login.credentials;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "person_credentials")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonCredentialsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "person_id", nullable = false, unique = true, updatable = false)
    private String personId;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;
}
