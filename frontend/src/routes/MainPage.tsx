import React from "react";
import withAuthRedirect from "../components/withAuthRedirect.tsx";
import Navigation from "../components/Navigation/Navigation.tsx";

const MainPageComponent: React.FC = () => {
    return (<div>
        <Navigation
            active='chats'
            items={[]}
        >
            <div>Aside header content</div>
        </Navigation>
    </div>)
};

const MainPage = withAuthRedirect(MainPageComponent);
export default MainPage;
