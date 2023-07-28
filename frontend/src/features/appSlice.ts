import {createSlice} from '@reduxjs/toolkit';
import {localStorageGetBoolean} from "./constants.ts";

interface AppState {
    asideHeaderCompact: boolean;
    settingsPanelVisible: boolean;
}

const initialState: AppState = {
    asideHeaderCompact: localStorageGetBoolean("asideHeaderCompact"),
    settingsPanelVisible: localStorageGetBoolean("settingsPanelVisible"),
};

const appSlice = createSlice({
    name: 'app',
    initialState,
    reducers: {
        switchCompact(state) {
            const value = !state.asideHeaderCompact
            state.asideHeaderCompact = value
            localStorage.setItem("asideHeaderCompact", String(value))
        },
        switchVisibility(state) {
            const value = !state.settingsPanelVisible
            state.settingsPanelVisible = value
            localStorage.setItem("settingsPanelVisible", String(value))
        }
    }
});

export const {switchCompact, switchVisibility} = appSlice.actions;
export default appSlice.reducer;
