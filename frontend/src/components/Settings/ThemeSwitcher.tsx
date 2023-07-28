import React from "react";
import {Switch} from "@gravity-ui/uikit";
import {useAppDispatch, useAppSelector} from "../../features/redux/hooks.ts";
import {darkTheme, lightTheme, Theme} from "../../features/redux/themeSlice.ts";

const ThemeSwitcher: React.FC = () => {
    const theme: Theme = useAppSelector((state) => state.theme.theme);
    const dispatch = useAppDispatch();
    return (<div>
        <div>
            <label htmlFor="theme-switcher">Light/Dark theme</label>
        </div>
        <div>
            <Switch
                id="theme-switcher"
                checked={theme === Theme.DARK}
                title="Light/Dark theme"
                onUpdate={(checked) => checked ? dispatch(darkTheme()) : dispatch(lightTheme())}
            />
        </div>
    </div>);
};

export default ThemeSwitcher;