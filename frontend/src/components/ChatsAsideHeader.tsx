import React from "react";
// import {Switch} from "@gravity-ui/uikit";
// import {useAppDispatch, useAppSelector} from "../features/hooks.ts";
// import {darkTheme, lightTheme, Theme} from "../features/themeSlice.ts";

// eslint-disable-next-line @typescript-eslint/no-empty-interface
interface ChatsAsideHeaderProps {
}

// Является foldable менюшкой в левой части экрана
// Занимает 40px в сложенном виде и 25% в разложенном
// По дефолту разложен (параметр в localStorage)

const ChatsAsideHeader: React.FC<ChatsAsideHeaderProps> = () => {
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

export default ChatsAsideHeader;
