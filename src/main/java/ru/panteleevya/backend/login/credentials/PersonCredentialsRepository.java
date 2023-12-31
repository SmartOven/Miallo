package ru.panteleevya.backend.login.credentials;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonCredentialsRepository extends MongoRepository<PersonCredentialsDocument, Long> {
    Optional<PersonCredentialsDocument> findByLogin(String login);

    boolean existsByLogin(String login);
}
