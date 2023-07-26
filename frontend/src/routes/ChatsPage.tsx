import React from "react";
import withAuthRedirect from "../components/withAuthRedirect.tsx";

// eslint-disable-next-line @typescript-eslint/no-empty-interface
interface DialogsProps {
}

const ChatsPageComponent: React.FC<DialogsProps> = () => {
    return (<h1>This is the dialogs page</h1>)
};

const ChatsPage = withAuthRedirect(ChatsPageComponent);
export default ChatsPage;
