import React from "react";
import withAuthRedirect from "../components/withAuthRedirect.tsx";

// eslint-disable-next-line @typescript-eslint/no-empty-interface
interface DialogsProps {
}

const DialogsComponent: React.FC<DialogsProps> = () => {
    return (<h1>This is the dialogs page</h1>)
};

const Dialogs = withAuthRedirect(DialogsComponent);
export default Dialogs;
