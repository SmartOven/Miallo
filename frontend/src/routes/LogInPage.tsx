import React from 'react';
import LogInForm from "../components/LoginForm/LogInForm.tsx";
import {useAppDispatch} from "../features/redux/hooks.ts";
import {executeFetch, RequestMethod} from "../features/fetch.ts";
import {LoginResponse} from "../features/constants.ts";
import {personIdSet, tokenSet} from "../features/redux/personSlice.ts";
import {useNavigate} from "react-router-dom";

// eslint-disable-next-line @typescript-eslint/no-empty-interface
interface RegisterFormProps {
}

const LogInPage: React.FC<RegisterFormProps> = () => {
    const dispatch = useAppDispatch();
    const navigate = useNavigate();

    const logInPerson = (login: string, password: string) => {
        void executeFetch('/api/login/sign-in/credentials', RequestMethod.POST, {
            login,
            password,
        }).then(async response => {
            if (!response.ok) {
                console.error("Couldn't sign in a person")
                return;
            }
            const signInResponse = await response.json() as LoginResponse;
            dispatch(personIdSet(signInResponse.personId));
            dispatch(tokenSet(signInResponse.token));
            console.log("Successfully signed in person")
            navigate("/");
        })
    };

    return (
        <div>
            <div style={{
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
                height: '100%',
                width: '100%'
            }}>
                <LogInForm title="Log in" type='log-in' onSubmit={logInPerson}/>
            </div>
        </div>
    );
};

export default LogInPage;
