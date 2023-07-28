/// <reference types="vite-plugin-svgr/client" />
import React from "react";
import {DrawerItemProps, FooterItem} from "@gravity-ui/navigation";
import {ReactComponent as settingsIcon} from '../../svg/settings-icon.svg'
import SettingsPanel from "../Settings/SettingsPanel.tsx";

export const getSettingsPanelProps = (visible: boolean): DrawerItemProps[] => {
    return [{
        id: 'settings-panel',
        content: <SettingsPanel/>,
        visible: visible,
        direction: 'left'
    }]
}

export const getRenderAsideHeaderFooter = (onSettingsClick: () => void) => {
    return (data: {
        size: number,
        compact: boolean,
        asideRef: React.RefObject<HTMLDivElement>,
    }) => {
        return (<>
            <FooterItem
                compact={data.compact}
                item={{
                    id: 'settings',
                    title: 'Settings',
                    onItemClick: onSettingsClick,
                    icon: settingsIcon,
                    iconSize: 26,
                }}
            />
        </>);
    }
}
