import React from "react";
import {AsideHeader, MenuItem} from "@gravity-ui/navigation";
import {asideHeaderLogo} from "../../features/constants.ts";
import {useAppDispatch, useAppSelector} from "../../features/redux/hooks.ts";
import {switchCompact, switchVisibility} from "../../features/redux/appSlice.ts";
import {getRenderAsideHeaderFooter, getSettingsPanelProps} from "./navigationUtils.tsx";

interface AsideHeaderWrapperProps {
    active: 'settings' | 'chats'
    items: MenuItem[];
    children: React.ReactElement
}

const Navigation: React.FC<AsideHeaderWrapperProps> = (props) => {
    const dispatch = useAppDispatch();
    const isCompact = useAppSelector((state) => state.app.asideHeaderCompact);
    const isSettingsPanelVisible = useAppSelector((state) => state.app.settingsPanelVisible);
    const panelItems = React.useMemo(() => getSettingsPanelProps(isSettingsPanelVisible), [isSettingsPanelVisible]);

    return (<AsideHeader
        compact={isCompact}
        logo={asideHeaderLogo}
        onChangeCompact={() => dispatch(switchCompact())}
        renderContent={() => props.children}
        panelItems={panelItems}
        menuItems={props.items}
        renderFooter={getRenderAsideHeaderFooter(() => dispatch(switchVisibility()))}
    />);
};

export default Navigation;
