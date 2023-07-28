import {LogoProps} from "@gravity-ui/navigation";
import logoIcon from '../svg/black-cat-icon.svg';

export const backendUri = 'http://localhost:8081'

export interface LoginResponse {
    personId: string;
    token: string;
}

export const asideHeaderLogo: LogoProps = {
    text: 'Miallo',
    textSize: 16,
    iconSrc: logoIcon,
    iconSize: 40
}

export const localStorageGetBoolean = (name: string) => {
    const value: string = localStorage.getItem(name) ?? "false"
    return value === "true"
}
