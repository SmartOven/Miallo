import React from "react";
import {AsideHeader, LogoProps, MenuItem} from "@gravity-ui/navigation";
import {useAppDispatch, useAppSelector} from "../../features/redux/hooks.ts";
import {switchCompact, switchPersonSearchModalOpen, switchVisibility} from "../../features/redux/appSlice.ts";
import {getRenderAsideHeaderFooter, getSettingsPanelProps} from "./navigationUtils.tsx";
import logoIcon from '../../svg/black-cat-icon.svg';
import {useNavigate} from "react-router-dom";
import {removePersonAndToken} from "../../features/redux/personSlice.ts";

interface NavigationProps {
    active: 'settings' | 'chats'
    items: MenuItem[];
    children: React.ReactElement
}

const Navigation: React.FC<NavigationProps> = (props) => {
    const navigate = useNavigate();
    const dispatch = useAppDispatch();
    const isCompact = useAppSelector((state) => state.app.asideHeaderCompact);
    const isSettingsPanelVisible = useAppSelector((state) => state.app.settingsPanelVisible);
    const panelItems = React.useMemo(() => getSettingsPanelProps(isSettingsPanelVisible), [isSettingsPanelVisible]);

    const asideHeaderLogo: LogoProps = {
        text: 'Miallo',
        textSize: 16,
        iconSrc: logoIcon,
        iconSize: 40,
        onClick: () => navigate('/')
    }

    const logOut = () => {
        dispatch(removePersonAndToken())
        navigate("/log-in")
    }

    return (<AsideHeader
        compact={isCompact}
        logo={asideHeaderLogo}
        onChangeCompact={() => dispatch(switchCompact())}
        renderContent={() => props.children}
        panelItems={panelItems}
        menuItems={props.items}
        renderFooter={getRenderAsideHeaderFooter(
            () => dispatch(switchPersonSearchModalOpen()),
            () => dispatch(switchVisibility()),
            logOut,
        )}
    />);
};

export default Navigation;
