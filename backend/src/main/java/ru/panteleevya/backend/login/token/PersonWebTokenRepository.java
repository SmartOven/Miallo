package ru.panteleevya.backend.login.token;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonWebTokenRepository extends MongoRepository<PersonWebTokenDocument, Long> {
    Optional<PersonWebTokenDocument> findByPersonId(String personId);

    Optional<PersonWebTokenDocument> findByToken(String token);
}
