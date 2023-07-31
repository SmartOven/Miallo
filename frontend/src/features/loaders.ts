import {executeFetch, RequestMethod} from "./fetch.ts";
import {Message} from "./interfaces/chat.ts";

export interface ChatInfo {
    chatId: string;
    messages: Message[];
}

export async function chatContentLoader(params: any): Promise<ChatInfo> {
    // eslint-disable-next-line @typescript-eslint/no-unsafe-member-access,@typescript-eslint/no-unsafe-assignment
    const chatId: string = params.params.chatId;
    const response = await executeFetch('/api/message/findLast100?chatId=' + chatId, RequestMethod.GET);
    if (!response.ok) {
        console.error("Couldn't fetch person data from chat with chatId=" + chatId);
        return {chatId: "", messages: []};
    }
    const messages = await response.json() as Message[];
    return {chatId: chatId, messages: messages};
}
