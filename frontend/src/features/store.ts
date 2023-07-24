import { configureStore } from '@reduxjs/toolkit';
import themeReducer from './themeSlice.ts';
import personReducer from "./personSlice.ts";

export const store = configureStore({
    reducer: {
        theme: themeReducer,
        person: personReducer
    },
});

export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;
