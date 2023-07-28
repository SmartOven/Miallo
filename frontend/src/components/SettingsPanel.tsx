import React from "react";
import ThemeSwitcher from "./ThemeSwitcher.tsx";
import "../styles/SettingsPanel.css"

const SettingsPanel: React.FC = () => {
    return (<div className="settings-panel">
        <h1>Settings panel</h1>
        <ThemeSwitcher/>
    </div>);
};

export default SettingsPanel;