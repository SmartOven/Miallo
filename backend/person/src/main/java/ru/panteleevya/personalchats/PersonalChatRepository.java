package ru.panteleevya.personalchats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonalChatRepository extends JpaRepository<PersonalChatEntity, Long> {
    boolean existsByOwnerPersonIdAndParticipantPersonId(String ownerPersonId, String participantPersonId);
    List<PersonalChatEntity> findAllByOwnerPersonId(String ownerPersonId);
    Optional<PersonalChatEntity> findByTitle(String title);
}
