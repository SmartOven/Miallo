package ru.panteleevya.groupchats;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "group_chats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupChatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "owner_person_id", nullable = false)
    private String ownerPersonId;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> membersPersonIds;
}
