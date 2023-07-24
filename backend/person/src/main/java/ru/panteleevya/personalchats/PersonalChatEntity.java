package ru.panteleevya.personalchats;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "personal_chats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalChatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "owner_person_id", nullable = false)
    private String ownerPersonId;

    @Column(name = "participant_person_id", nullable = false)
    private String participantPersonId;
}
