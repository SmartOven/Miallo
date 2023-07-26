import React from "react";
// import {Switch} from "@gravity-ui/uikit";
// import {useAppDispatch, useAppSelector} from "../features/hooks.ts";
// import {darkTheme, lightTheme, Theme} from "../features/themeSlice.ts";

// eslint-disable-next-line @typescript-eslint/no-empty-interface
interface AsideHeaderProps {
}

// TODO Добавить меню с чатами и настройками
const AsideHeader: React.FC<AsideHeaderProps> = () => {
    // const theme: Theme = useAppSelector((state) => state.theme.theme);
    // const dispatch = useAppDispatch();

    // function onSwitch(checked: boolean) {
    //     if (checked) {
    //         dispatch(darkTheme())
    //     } else {
    //         dispatch(lightTheme());
    //     }
    // }

    // return (
    //     <div>
    //         <div>
    //             <label htmlFor="theme-switcher">Light/Dark theme</label>
    //         </div>
    //         <div>
    //             <Switch
    //                 id="theme-switcher"
    //                 checked={theme === Theme.DARK}
    //                 title="Light/Dark theme"
    //                 onUpdate={onSwitch}
    //             />
    //         </div>
    //
    //     </div>
    // );
};

export default AsideHeader;
