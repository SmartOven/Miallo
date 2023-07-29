package ru.panteleevya.person;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends MongoRepository<PersonDocument, Long> {
    boolean existsByPersonId(String personId);

    boolean existsByNickname(String nickname);

    Optional<PersonDocument> findByPersonId(String personId);

    Optional<PersonDocument> findByNameOrSurnameOrNickname(String name, String surname, String nickname);
}
