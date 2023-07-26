import React from 'react';
import '../styles/App.css';
// import AsideHeaderWrapper from './AsideHeaderWrapper.tsx';
import Page from './Page.tsx';

// eslint-disable-next-line @typescript-eslint/no-empty-interface
interface AppProps {
}

const App: React.FC<AppProps> = () => {
    return (<Page/>);
};

export default App;
