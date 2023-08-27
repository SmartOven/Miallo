import React, {ChangeEvent, KeyboardEvent, useState} from 'react';
import {Button, Card, TextInput} from '@gravity-ui/uikit';
import "./LogInForm.css"
import {useNavigate} from "react-router-dom";

export enum LoginType {
    LOG_IN,
    SIGN_UP,
}

interface LoginFormProps {
    title: string;
    type: LoginType;
    onSubmit: (login: string, password: string) => void;
}

const LogInForm: React.FC<LoginFormProps> = ({title, type, onSubmit}) => {
    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const onChange = (event: ChangeEvent<HTMLInputElement>) => {
        const {name, value} = event.target;
        if (name === 'login') {
            setLogin(value);
        } else if (name === 'password') {
            setPassword(value);
        }
    };

    const onSubmitByKeyDown = (event: KeyboardEvent<HTMLInputElement>) => {
        if (event.key === 'Enter' && login.length > 0 && password.length > 0) {
            onSubmit(login, password);
        }
    };

    return (
        <div className="login-form-div">
            <Card className="card-stories">
                <div className="card-div card-content-stories">
                    <div style={{marginRight: '80px'}}>
                        <h1>{title}</h1>
                    </div>
                    <div style={{marginBottom: '8px'}}>
                        <TextInput
                            id="login-text-input"
                            name="login"
                            placeholder="Login"
                            size="l"
                            value={login}
                            onChange={onChange}
                            onKeyDown={onSubmitByKeyDown}
                        />
                    </div>
                    <div style={{marginBottom: '8px'}}>
                        <TextInput
                            id="password-text-input"
                            name="password"
                            placeholder="Password"
                            size="l"
                            type="password"
                            value={password}
                            onChange={onChange}
                            onKeyDown={onSubmitByKeyDown}
                        />
                    </div>
                    <div className="button-grid">
                        <Button
                            className="button-div"
                            view="action"
                            size="l"
                            type="submit"
                            onClick={() => onSubmit(login, password)}
                        >
                            {type === LoginType.LOG_IN ? "Log in" : "Sign up"}
                        </Button>
                        <div>
                            {/* Dummy element for using space */}
                        </div>
                        <Button
                            className="button-div"
                            view="outlined-info"
                            size="l"
                            type="submit"
                            onClick={() => navigate(type === LoginType.LOG_IN ? "/sign-up" : "/log-in")}
                        >
                            {type === LoginType.LOG_IN ? "Sign up" : "Log in"}
                        </Button>
                    </div>
                </div>
            </Card>
        </div>
    );
};

export default LogInForm;
