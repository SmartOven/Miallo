export interface Message {
    chatId: string;
    authorPersonId: string;
    text: string;
    timestamp: number;
}

export interface MessageDto {
    chatId: string;
    authorPersonId: string;
    text: string;
}
