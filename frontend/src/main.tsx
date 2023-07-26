import '@gravity-ui/uikit/styles/styles.css';
import React from 'react'
import ReactDOM from 'react-dom/client'
import './styles/main.css'
import App from "./components/App.tsx";
import {store} from "./features/store.ts";
import {Provider} from "react-redux";

ReactDOM.createRoot(document.getElementById('root')!).render(
    <React.StrictMode>
        <Provider store={store}>
            <App/>
        </Provider>
    </React.StrictMode>,
)
