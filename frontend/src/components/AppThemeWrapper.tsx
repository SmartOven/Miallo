import React from "react";
import {useAppSelector} from "../features/hooks.ts";
import App from "./App.tsx";
import {ThemeProvider} from "@gravity-ui/uikit";

const AppThemeWrapper: React.FC = () => {
    const theme = useAppSelector((state) => state.theme.theme);
    return (
        <ThemeProvider theme={theme}>
            <App/>
        </ThemeProvider>
    );
};

export default AppThemeWrapper;
