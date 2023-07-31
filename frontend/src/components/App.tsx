import React from "react";
import {useAppSelector} from "../features/redux/hooks.ts";
import {ThemeProvider} from "@gravity-ui/uikit";
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import MainPage from "../routes/MainPage.tsx";
import ErrorPage from "../ErrorPage.tsx";
import SignUpPage from "../routes/SignUpPage.tsx";
import SignInPage from "../routes/SignInPage.tsx";
import ChatContent from "./ChatContent.tsx";
import {chatContentLoader} from "../features/loaders.ts";

const router = createBrowserRouter([
    {
        path: "/",
        element: <MainPage/>,
        errorElement: <ErrorPage/>,
        children: [
            {
                path: ":chatId",
                element: <ChatContent/>,
                loader: chatContentLoader,
            },
        ],
    },
    {
        path: "/sign-up",
        element: <SignUpPage/>,
    },
    {
        path: "/sign-in",
        element: <SignInPage/>,
    },
]);

const App: React.FC = () => {
    const theme = useAppSelector((state) => state.theme.theme);
    return (
        <ThemeProvider theme={theme}>
            <RouterProvider router={router}/>
        </ThemeProvider>
    );
};

export default App;
