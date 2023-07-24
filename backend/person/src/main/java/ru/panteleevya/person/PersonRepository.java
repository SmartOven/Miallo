package ru.panteleevya.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    boolean existsByPersonId(String personId);
    boolean existsByNickname(String nickname);
    Optional<PersonEntity> findByPersonId(String personId);
    Optional<PersonEntity> findByNameOrSurnameOrNickname(String name, String surname, String nickname);
}
