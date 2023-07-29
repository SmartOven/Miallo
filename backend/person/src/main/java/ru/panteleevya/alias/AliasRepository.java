package ru.panteleevya.alias;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AliasRepository extends JpaRepository<AliasEntity, Long> {
    boolean existsByPersonIdAndAliasedPersonId(String personId, String aliasedPersonId);
}
