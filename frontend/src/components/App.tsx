import React from 'react';
import '../styles/App.css';
import Header from './Header';
import Content from './Content';

// eslint-disable-next-line @typescript-eslint/no-empty-interface
interface AppProps {
}

const App: React.FC<AppProps> = () => {
    return (<div className="root-grid">
        <div className="root-grid-row">
            <Header/>
        </div>
        <div className="root-grid-row">
            <Content/>
        </div>
    </div>);
};

export default App;
