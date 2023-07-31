import {createSlice} from '@reduxjs/toolkit';
import {localStorageGetBoolean} from "../constants.ts";

interface AppState {
    asideHeaderCompact: boolean;
    settingsPanelVisible: boolean;
    personSearchModalOpen: boolean;
}

const initialState: AppState = {
    asideHeaderCompact: localStorageGetBoolean("asideHeaderCompact"),
    settingsPanelVisible: localStorageGetBoolean("settingsPanelVisible"),
    personSearchModalOpen: false,
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
        },
        switchPersonSearchModalOpen(state) {
            state.personSearchModalOpen = !state.personSearchModalOpen
        }
    }
});

export const {switchCompact, switchVisibility, switchPersonSearchModalOpen} = appSlice.actions;
export default appSlice.reducer;
