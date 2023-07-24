package ru.panteleevya.login.model.credentials;

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

    public Optional<PersonCredentialsEntity> findByLoginAndPassword(String login, String password) {
        Optional<PersonCredentialsEntity> entityOptional = personCredentialsRepository.findByLogin(login);
        if (entityOptional.isEmpty()) {
            return Optional.empty();
        }
        PersonCredentialsEntity personCredentialsEntity = entityOptional.get();
        String encodedPassword = passwordEncoder.encode(password);
        if (!passwordEncoder.matches(password, encodedPassword)) {
            return Optional.empty();
        }
        return Optional.of(personCredentialsEntity);
    }

    public PersonCredentialsEntity create(PersonCredentials personCredentials) {
        String login = personCredentials.getLogin();
        if (personCredentialsRepository.existsByLogin(login)) {
            throw new IllegalArgumentException(String.format("Person credentials with login=%s already exists", login));
        }
        String personId = UUID.randomUUID().toString();
        String encodedPassword = passwordEncoder.encode(personCredentials.getPassword());
        return personCredentialsRepository.save(new PersonCredentialsEntity(null, personId, login, encodedPassword));
    }

    public PersonCredentialsEntity updatePassword(Long id, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        PersonCredentialsEntity personCredentialsEntity = personCredentialsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Person credentials with id=%s doesn't exist", id)));
        personCredentialsEntity.setPassword(encodedPassword);
        return personCredentialsRepository.save(personCredentialsEntity);
    }

    public void delete(String login, String password) {
        Optional<PersonCredentialsEntity> entityOptional = findByLoginAndPassword(login, password);
        if (entityOptional.isEmpty()) {
            return;
        }
        personCredentialsRepository.delete(entityOptional.get());
    }
}
