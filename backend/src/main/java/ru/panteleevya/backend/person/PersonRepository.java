package ru.panteleevya.backend.person;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends MongoRepository<PersonDocument, Long> {
    boolean existsByPersonId(String personId);

    boolean existsByNickname(String nickname);

    Optional<PersonDocument> findByPersonId(String personId);

    Optional<PersonDocument> findByNickname(String nickname);

//    Optional<PersonDocument> findByNameOrSurnameOrNickname(String name, String surname, String nickname);

    @Query("{$or: [{name: {$regex: ?0, $options: 'i'}}, {surname: {$regex: ?0, $options: 'i'}}, {nickname: {$regex: ?0, $options: 'i'}}]}")
    List<PersonDocument> findByQuery(String query);
}
