package ru.panteleevya.backend.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String personId;
    private String surname;
    private String name;
    private String nickname;
    private String bio;
    private List<Alias> aliases;
    private List<PersonalChatInfo> personalChats;
    private List<GroupChatInfo> groupChats;
}
