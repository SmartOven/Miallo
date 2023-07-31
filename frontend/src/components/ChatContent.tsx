import React, {KeyboardEvent, useEffect, useRef, useState} from "react";
import {useLoaderData} from "react-router-dom";
import {Message, MessageDto} from "../features/interfaces/chat.ts";
import {useAppSelector} from "../features/redux/hooks.ts";
import {Button, Card, TextInput} from "@gravity-ui/uikit";
import {ChatInfo} from "../features/loaders.ts";
import {executeFetch, RequestMethod} from "../features/fetch.ts";
import "../styles/ChatContent.css"

const ChatContent: React.FC = () => {
    // @ts-ignore
    const chatContent: ChatInfo = useLoaderData();
    const messages = chatContent.messages;
    const chatId = chatContent.chatId;
    const personId = useAppSelector((state) => state.person.personId);
    const theme = useAppSelector((state) => state.theme.theme);
    const chatCardClass = theme === 'light' ? 'chat-card-light-theme' : 'chat-card-dark-theme'
    const [messageText, setMessageText] = useState('')

    const sendMessage = (messageDto: MessageDto) => {
        void executeFetch('/api/message/create', RequestMethod.POST, messageDto)
            .then(async response => {
                if (!response.ok) {
                    console.error("Couldn't send message")
                    return;
                }
                const message = await response.json() as Message;
                messages.push(message)
            })
    }

    const onSubmitSendMessage = () => {
        if (messageText.length === 0) {
            return;
        }
        const message: MessageDto = {
            chatId: chatId,
            authorPersonId: personId,
            text: messageText,
        }
        sendMessage(message)
        setMessageText('')
    }

    const onSubmitByKeyDown = (event: KeyboardEvent<HTMLInputElement>) => {
        if (event.ctrlKey || event.metaKey) {
            return;
        }
        if (event.key === 'Enter') {
            onSubmitSendMessage()
        }
    };

    const scrollRef = useRef<HTMLDivElement>(null);

    // Здесь прокручиваем элемент в самый низ
    useEffect(() => {
        if (scrollRef.current) {
            scrollRef.current.scrollTop = scrollRef.current.scrollHeight;
        }
    }, [chatContent]);

    return (<div className="chat-content-grid">
        <div ref={scrollRef} className="messages-div">
            {messages
                .slice(0) // Создаем копию массива, чтобы не изменять исходный массив
                .reverse() // Инвертируем порядок элементов в массиве (в обратном порядке)
                .map((message, index) => (
                    <div key={'message-' + String(index)} className="chat-message">
                        <div className={message.authorPersonId === personId ? 'owning-message' : 'not-owning-message'}>
                            <Card className={chatCardClass + " card-stories"}>
                                <div className="chat-card-content card-content-stories">
                                    {message.text}
                                </div>
                            </Card>
                        </div>
                    </div>
                ))}
        </div>
        <div className="write-message-form">
            <TextInput
                id="write-message-text-input"
                name="write-message"
                placeholder="Write anything to your friend..."
                size="l"
                value={messageText}
                onChange={(event) => setMessageText(event.target.value)}
                onKeyDown={onSubmitByKeyDown}
            />
            <Button
                view="action"
                size="l"
                type="submit"
                onClick={onSubmitSendMessage}
            >
                Send
            </Button>
        </div>
    </div>);
};

export default ChatContent;
