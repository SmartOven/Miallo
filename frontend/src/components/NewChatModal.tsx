import React, {KeyboardEvent, useState} from 'react';
import {Button, Modal, TextInput} from "@gravity-ui/uikit";
import {useAppDispatch, useAppSelector} from "../features/redux/hooks.ts";
import {switchPersonSearchModalOpen} from "../features/redux/appSlice.ts";
import "../styles/PersonSearchModel.css"
import {executeFetch, RequestMethod} from "../features/fetch.ts";
import {Person} from "../features/interfaces/person.ts";
import {FooterItem} from "@gravity-ui/navigation";
import {ReactComponent as userIcon} from "../svg/user-icon.svg";
import {useNavigate} from "react-router-dom";

const NewChatModal: React.FC = () => {
    const dispatch = useAppDispatch();
    const navigate = useNavigate();
    const isOpen = useAppSelector((state) => state.app.personSearchModalOpen);
    const [query, setQuery] = useState('');
    const [lastQuery, setLastQuery] = useState('');
    const [foundPersons, setFoundPersons] = useState<Person[]>([]);

    const searchForPersons = () => {
        void executeFetch('/api/person/search?query=' + query, RequestMethod.GET)
            .then(async response => {
                if (!response.ok) {
                    console.error("Couldn't search for a person")
                    return;
                }
                const foundPersonsResponse = await response.json() as Person[];
                console.log(foundPersonsResponse)
                setFoundPersons(foundPersonsResponse);
            })
    }

    // FIXME сделать норм создание чата со стартовым "Привет!"
    const createChat = (person: Person) => {
        const chatId = "new-chat-id"
        console.log("Creating new chat with chatId=" + chatId + " and person nickname=" + person.nickname)
        dispatch(switchPersonSearchModalOpen())
        navigate('/' + chatId)
    }

    const onClose = () => {
        dispatch(switchPersonSearchModalOpen())
        setQuery('')
        setLastQuery('')
    }

    const onSubmitSearchQuery = () => {
        if (query.length === 0 || query === lastQuery) {
            return;
        }
        console.log("Searching for " + query)
        searchForPersons()
        setLastQuery(query)
    }

    const onSubmitByKeyDown = (event: KeyboardEvent<HTMLInputElement>) => {
        if (event.key === 'Enter') {
            onSubmitSearchQuery()
        }
    };

    const renderResults = () => {
        if (foundPersons.length === 0) {
            return (<div className="empty-persons-result">Nothing to show</div>)
        }
        return (<div>
            {foundPersons.map((person) => (
                <FooterItem
                    key={'key-' + person.personId}
                    compact={false}
                    item={{
                        id: 'id-' + person.personId,
                        title: person.name + ' ' + person.surname,
                        onItemClick: () => createChat(person),
                        icon: userIcon,
                        iconSize: 26,
                    }}
                />
            ))}
        </div>)
    }

    return (
        <div className="modal-div">
            <Modal
                open={isOpen}
                onClose={onClose}
            >
                <div className="search-form">
                    <TextInput
                        id="person-search-text-input"
                        name="search"
                        placeholder="🔎 Search for person"
                        size="l"
                        value={query}
                        onChange={(event) => setQuery(event.target.value)}
                        onKeyDown={onSubmitByKeyDown}
                    />
                    <Button
                        view="action"
                        size="l"
                        type="submit"
                        onClick={onSubmitSearchQuery}
                    >
                        Search
                    </Button>
                </div>

                <div
                    style={{
                        padding: 10
                    }}
                >
                    {renderResults()}
                </div>
            </Modal>
        </div>
    );
};

export default NewChatModal;
