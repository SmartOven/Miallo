/// <reference types="vite-plugin-svgr/client" />
import React from "react";
import {DrawerItemProps, FooterItem} from "@gravity-ui/navigation";
import {ReactComponent as settingsIcon} from '../../svg/settings-icon.svg'
import {ReactComponent as newChatIcon} from '../../svg/write-icon.svg'
import {ReactComponent as logOutIcon} from '../../svg/logout.svg'
import SettingsPanel from "../Settings/SettingsPanel.tsx";

export const getSettingsPanelProps = (visible: boolean): DrawerItemProps[] => {
    return [{
        id: 'settings-panel',
        content: <SettingsPanel/>,
        visible: visible,
        direction: 'left'
    }]
}

export const getRenderAsideHeaderFooter = (
    onNewChatClick: () => void,
    onSettingsClick: () => void,
    onLogOutClick: () => void,
) => {
    return (data: {
        size: number,
        compact: boolean,
        asideRef: React.RefObject<HTMLDivElement>,
    }) => {
        return (<div>
            <FooterItem
                compact={data.compact}
                item={{
                    id: 'new-chat',
                    title: 'New chat',
                    onItemClick: onNewChatClick,
                    icon: newChatIcon,
                    iconSize: 26,
                    type: 'action',
                }}
            />
            <FooterItem
                compact={data.compact}
                item={{
                    id: 'settings',
                    title: 'Settings',
                    onItemClick: onSettingsClick,
                    icon: settingsIcon,
                    iconSize: 26,
                    type: 'action',
                }}
            />
            <FooterItem
                compact={data.compact}
                item={{
                    id: 'log-out',
                    title: 'Log out',
                    onItemClick: onLogOutClick,
                    icon: logOutIcon,
                    iconSize: 26,
                    type: 'action',
                }}
            />
        </div>);
    }
}
