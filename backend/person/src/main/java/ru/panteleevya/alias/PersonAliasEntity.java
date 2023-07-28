package ru.panteleevya.alias;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "person_aliases")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonAliasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "person_id", nullable = false)
    private String personId;

    @Column(name = "aliased_person_id", nullable = false, unique = true)
    private String aliasedPersonId;

    @Column(name = "aliased_surname", nullable = false)
    private String aliasedSurname;

    @Column(name = "aliased_name", nullable = false)
    private String aliasedName;
}
