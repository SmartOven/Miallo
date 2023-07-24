package ru.panteleevya.groupchats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupChatRepository extends JpaRepository<GroupChatEntity, Long> {
    Optional<GroupChatEntity> findByTitleAndOwnerPersonId(String title, String ownerPersonId);
    List<GroupChatEntity> findAllByOwnerPersonId(String ownerPersonId);
    Optional<GroupChatEntity> findByTitle(String title);
}
