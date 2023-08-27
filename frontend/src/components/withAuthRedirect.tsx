import {useNavigate} from "react-router-dom";
import React, {useEffect} from "react";
import {useAppSelector} from "../features/redux/hooks.ts";

const withAuthRedirect = (WrappedComponent: React.ComponentType) => {
    const WithAuthRedirect: React.FC = (props) => {
        const navigate = useNavigate();
        const personId = useAppSelector((state) => state.person.personId);
        const token = useAppSelector((state) => state.person.token);
        useEffect(() => {
            if (token == "" || personId == "") {
                navigate("/log-in");
            }
        })
        return <WrappedComponent {...props} />;
    };
    return WithAuthRedirect;
};

export default withAuthRedirect;
