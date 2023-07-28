import {createSlice} from '@reduxjs/toolkit';

export enum Theme {
    DARK = 'dark',
    LIGHT = 'light',
}

interface ThemeState {
    theme: Theme;
}

const initialState: ThemeState = {
    theme: getInitialTheme(),
};

function getInitialTheme() {
    const preferredTheme = localStorage.getItem("preferredTheme");
    if (preferredTheme === null || preferredTheme === 'light') {
        return Theme.LIGHT
    }
    return Theme.DARK
}

const themeSlice = createSlice({
    name: 'theme',
    initialState,
    reducers: {
        darkTheme(state) {
            state.theme = Theme.DARK;
            localStorage.setItem("preferredTheme", "dark");
        },
        lightTheme(state) {
            state.theme = Theme.LIGHT;
            localStorage.setItem("preferredTheme", "light");
        },
    },
});

export const {darkTheme, lightTheme} = themeSlice.actions;
export default themeSlice.reducer;
