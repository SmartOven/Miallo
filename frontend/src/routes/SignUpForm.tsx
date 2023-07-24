import React from 'react';
import LoginForm from "../components/LoginForm.tsx";
import {executeFetch, RequestMethod} from "../features/fetch.ts";
import {personIdSet, tokenSet} from "../features/personSlice.ts";
import {useAppDispatch} from "../features/hooks.ts";
import {LoginResponse} from "../features/constants.ts";
import {useNavigate} from "react-router-dom";

// eslint-disable-next-line @typescript-eslint/no-empty-interface
interface RegisterFormProps {
}

const SignUpForm: React.FC<RegisterFormProps> = () => {
    const dispatch = useAppDispatch();
    const navigate = useNavigate();

    const signUpPerson = (login: string, password: string) => {
        void executeFetch('/api/sign-up', RequestMethod.POST, {
            login,
            password,
        }).then(async response => {
            if (!response.ok) {
                console.error("Couldn't sign up a person")
                return;
            }
            const signUpResponse = await response.json() as LoginResponse;
            dispatch(personIdSet(signUpResponse.personId));
            dispatch(tokenSet(signUpResponse.token));
            console.log("Successfully signed up person")
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
                <LoginForm title="Sign up" buttonText="Sign up" onSubmit={signUpPerson}/>
            </div>
        </div>
    );
};

export default SignUpForm;
