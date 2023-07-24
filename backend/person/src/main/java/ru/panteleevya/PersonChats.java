package ru.panteleevya;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.panteleevya.groupchats.GroupChat;
import ru.panteleevya.personalchats.PersonalChat;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonChats {
    private List<PersonalChat> personalChats;
    private List<GroupChat> groupChats;
}
