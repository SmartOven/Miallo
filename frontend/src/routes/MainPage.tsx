import React, {useEffect, useState} from "react";
import withAuthRedirect from "../components/withAuthRedirect.tsx";
import Navigation from "../components/Navigation/Navigation.tsx";
import {executeFetch, RequestMethod} from "../features/fetch.ts";
import {MenuItem} from "@gravity-ui/navigation";
import {ReactComponent as personalChatIcon} from "../svg/user-icon.svg";
import {ReactComponent as groupChatIcon} from "../svg/chats-icon.svg";
import {useAppSelector} from "../features/redux/hooks.ts";
import NewChatModal from "../components/NewChatModal.tsx";
import {Outlet, useNavigate} from "react-router-dom";

interface Chat {
    chatId: string;
    title: string;
    type: 'PERSONAL' | 'GROUP';
    changedAt: number;
}

const MainPageComponent: React.FC = () => {
    const navigate = useNavigate();
    const [chatsItems, setChatsItems] = useState<MenuItem[]>([])
    const personId = useAppSelector((state) => state.person.personId);

    const chatsToItems = (chats: Chat[]): MenuItem[] => {
        return chats.map(chat => {
            return {
                id: chat.chatId,
                title: chat.title,
                icon: chat.type === 'PERSONAL' ? personalChatIcon : groupChatIcon,
                onItemClick: (item) => navigate('/' + item.id)
            };
        });
    }

    const fetchChats = () => {
        void executeFetch(
            '/api/chat/findAll?personId=' + personId,
            RequestMethod.GET,
        ).then(async response => {
            if (!response.ok) {
                console.error("Couldn't fetch chats")
                return;
            }
            const chatsResponse = await response.json() as Chat[];
            console.log("Successfully fetched chats")
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
            <div id="content">
                <Outlet />
            </div>
        </Navigation>
        <NewChatModal/>
    </div>)
};

const MainPage = withAuthRedirect(MainPageComponent);
export default MainPage;
