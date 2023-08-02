export interface Alias {
    aliasedPersonId: string;
    aliasedSurname: string;
    aliasedName: string;
}

export interface PersonalChatInfo {
    personId: string;
    chatId: string;
}

export interface GroupChatInfo {
    chatId: string;
}

export interface Person {
    personId: string;
    surname: string;
    name: string;
    nickname: string;
    bio: string;
    aliases: Alias[];
    personalChats: PersonalChatInfo[];
    groupChats: GroupChatInfo[];
}

export const nullPerson: Person = {
    personId: "",
    surname: "",
    name: "",
    nickname: "",
    bio: "",
    aliases: [],
    personalChats: [],
    groupChats: [],
}