package ru.panteleevya.backend.login.credentials;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonCredentialsService {
    private final PersonCredentialsRepository personCredentialsRepository;
    private final PasswordEncoder passwordEncoder;

    public PersonCredentialsService(
            PersonCredentialsRepository personCredentialsRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.personCredentialsRepository = personCredentialsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<PersonCredentialsDocument> findByLoginAndPassword(String login, String password) {
        Optional<PersonCredentialsDocument> entityOptional = personCredentialsRepository.findByLogin(login);
        if (entityOptional.isEmpty()) {
            return Optional.empty();
        }
        PersonCredentialsDocument personCredentialsDocument = entityOptional.get();
        String encodedPassword = passwordEncoder.encode(password);
        if (!passwordEncoder.matches(password, encodedPassword)) {
            return Optional.empty();
        }
        return Optional.of(personCredentialsDocument);
    }

    public PersonCredentialsDocument create(PersonCredentials personCredentials) {
        String login = personCredentials.getLogin();
        if (personCredentialsRepository.existsByLogin(login)) {
            throw new IllegalArgumentException(String.format("Person credentials with login=%s already exists", login));
        }
        String personId = UUID.randomUUID().toString();
        String encodedPassword = passwordEncoder.encode(personCredentials.getPassword());
        return personCredentialsRepository.save(new PersonCredentialsDocument(null, personId, login, encodedPassword));
    }

    public PersonCredentialsDocument updatePassword(Long id, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        PersonCredentialsDocument personCredentialsDocument = personCredentialsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Person credentials with id=%s doesn't exist", id)));
        personCredentialsDocument.setPassword(encodedPassword);
        return personCredentialsRepository.save(personCredentialsDocument);
    }

    public void delete(String login, String password) {
        Optional<PersonCredentialsDocument> entityOptional = findByLoginAndPassword(login, password);
        if (entityOptional.isEmpty()) {
            return;
        }
        personCredentialsRepository.delete(entityOptional.get());
    }
}
