import {useNavigate} from "react-router-dom";
import React, {useEffect} from "react";

const withAuthRedirect = (WrappedComponent: React.ComponentType) => {
    const WithAuthRedirect: React.FC = (props) => {
        const navigate = useNavigate();
        useEffect(() => {
            if (localStorage.getItem("token") === null) {
                navigate("/sign-in");
            }
        })
        return <WrappedComponent {...props} />;
    };

    return WithAuthRedirect;
};

export default withAuthRedirect;
