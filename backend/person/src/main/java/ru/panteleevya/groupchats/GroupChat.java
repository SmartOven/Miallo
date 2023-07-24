package ru.panteleevya.groupchats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupChat {
    private String title;
    private String ownerPersonId;
    private Set<String> membersPersonIds;
}
