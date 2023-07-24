package ru.panteleevya.personalchats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalChat {
    private String title;
    private String ownerPersonId;
    private String participantPersonId;
}
