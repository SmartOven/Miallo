package ru.panteleevya.backend.message;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chat_id", nullable = false, updatable = false)
    private String chatId;

    @Column(name = "author_person_id", nullable = false, updatable = false)
    private String authorPersonId;

    @Column(name = "text", nullable = false, length = 10000)
    private String text;

    @Column(name = "timestamp", nullable = false)
    private Long timestamp;
}
