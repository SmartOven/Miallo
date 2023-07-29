package ru.panteleevya.login.credentials;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonCredentialsRepository extends JpaRepository<PersonCredentialsEntity, Long> {
    Optional<PersonCredentialsEntity> findByLogin(String login);

    boolean existsByLogin(String login);
}
