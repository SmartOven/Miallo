package ru.panteleevya.login.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonWebTokenRepository extends JpaRepository<PersonWebTokenEntity, Long> {
    Optional<PersonWebTokenEntity> findByPersonId(String personId);
    Optional<PersonWebTokenEntity> findByToken(String token);
}
