package ru.panteleevya.alias;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonAliasRepository extends JpaRepository<PersonAliasEntity, Long> {
    boolean existsByPersonIdAndAliasedPersonId(String personId, String aliasedPersonId);
}
