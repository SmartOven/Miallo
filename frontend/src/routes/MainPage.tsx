import React, {useEffect, useState} from "react";
import withAuthRedirect from "../components/withAuthRedirect.tsx";
import Navigation from "../components/Navigation/Navigation.tsx";
import {executeFetch, RequestMethod} from "../features/fetch.ts";
import {MenuItem} from "@gravity-ui/navigation";
import {ReactComponent as personalChatIcon} from "../svg/user-icon.svg";
import {useAppSelector} from "../features/redux/hooks.ts";

interface PersonalChat {
    title: string;
    ownerPersonId: string;
    participantPersonId: string;
}

interface GroupChat {
    title: string;
    ownerPersonId: string;
    membersPersonIds: string[];
}

interface ChatsResponse {
    personalChats: PersonalChat[];
    groupChats: GroupChat[];
}

const chatsToItems = (chats: ChatsResponse): MenuItem[] => {
    const personalChats: MenuItem[] = chats.personalChats.map((chat, index) => {
        return {
            id: 'personal-chat-' + String(index),
            title: chat.title,
            icon: personalChatIcon
        };
    });
    const groupChats: MenuItem[] = chats.groupChats.map((chat, index) => {
        const item: MenuItem = {
            id: 'group-chat-' + String(index),
            title: chat.title,
            icon: personalChatIcon
        }
        return item;
    });
    return [...personalChats, ...groupChats];
}

const MainPageComponent: React.FC = () => {
    const [chatsItems, setChatsItems] = useState<MenuItem[]>([])
    const personId = useAppSelector((state) => state.person.personId);

    const fetchChats = () => {
        console.log(personId)
        void executeFetch(
            '/api/chats/list?personId=' + personId,
            RequestMethod.GET,
        ).then(async response => {
            if (!response.ok) {
                console.error("Couldn't fetch chats")
                return;
            }
            const chatsResponse = await response.json() as ChatsResponse;
            console.log("Successfully fetched chats")
            console.log(chatsResponse)
            setChatsItems(chatsToItems(chatsResponse))
        })
    }

    useEffect(() => {
        fetchChats();
    }, [])

    return (<div>
        <Navigation
            active='chats'
            items={chatsItems}
        >
            <div>Aside header content</div>
        </Navigation>
    </div>)
};

const MainPage = withAuthRedirect(MainPageComponent);
export default MainPage;
