import React from "react";
import {useAppSelector} from "../features/hooks.ts";
import {ThemeProvider} from "@gravity-ui/uikit";
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import ChatsPage from "../routes/ChatsPage.tsx";
import ErrorPage from "../ErrorPage.tsx";
import SignUpPage from "../routes/SignUpPage.tsx";
import SignInPage from "../routes/SignInPage.tsx";

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

const App: React.FC = () => {
    const theme = useAppSelector((state) => state.theme.theme);
    return (
        <ThemeProvider theme={theme}>
            <RouterProvider router={router}/>
        </ThemeProvider>
    );
};

export default App;
