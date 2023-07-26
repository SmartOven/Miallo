import React from 'react';
import {
    createBrowserRouter,
    RouterProvider,
} from "react-router-dom";
import ErrorPage from "../ErrorPage.tsx";
import SignUpPage from "../routes/SignUpPage.tsx";
import SignInPage from "../routes/SignInPage.tsx";
import ChatsPage from "../routes/ChatsPage.tsx";

const router = createBrowserRouter([
    {
        path: "/",
        element: <ChatsPage/>,
        errorElement: <ErrorPage/>,
    },
    {
        path: "/sign-up",
        element: <SignUpPage/>,
    },
    {
        path: "/sign-in",
        element: <SignInPage/>,
    },
    {
        path: "/settings",
        element: <div>Settings page</div>
    },
]);

const Page: React.FC = () => {
    return (<RouterProvider router={router}/>)
};

export default Page;
