import React from 'react';
import {
    createBrowserRouter,
    RouterProvider,
} from "react-router-dom";
import ErrorPage from "../ErrorPage.tsx";
import SignUpForm from "../routes/SignUpForm.tsx";
import SignInForm from "../routes/SignInForm.tsx";
import Dialogs from "../routes/Dialogs.tsx";

const router = createBrowserRouter([
    {
        path: "/",
        element: <Dialogs/>,
        errorElement: <ErrorPage/>,
    },
    {
        path: "/sign-up",
        element: <SignUpForm/>,
    },
    {
        path: "/sign-in",
        element: <SignInForm/>,
    },
]);

// eslint-disable-next-line @typescript-eslint/no-empty-interface
interface ContentProps {
}

const Content: React.FC<ContentProps> = () => {
    return (<RouterProvider router={router}/>)
};

export default Content;
