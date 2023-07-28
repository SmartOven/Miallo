import {createSlice, PayloadAction} from '@reduxjs/toolkit';

interface PersonState {
    personId: string;
    token: string;
}

const initialState: PersonState = {
    personId: localStorage.getItem("personId") ?? "",
    token: localStorage.getItem("token") ?? "",
};

const personSlice = createSlice({
    name: 'person',
    initialState,
    reducers: {
        personIdSet(state, action: PayloadAction<string>) {
            state.personId = action.payload;
            localStorage.setItem("personId", state.personId);
        },
        tokenSet(state, action: PayloadAction<string>) {
            state.token = action.payload;
            localStorage.setItem("token", state.token);
        },
    },
});

export const {personIdSet, tokenSet} = personSlice.actions;
export default personSlice.reducer;
