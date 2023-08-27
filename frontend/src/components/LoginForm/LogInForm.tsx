import React, {ChangeEvent, KeyboardEvent, useState} from 'react';
import {Button, Card, TextInput} from '@gravity-ui/uikit';
import "./LogInForm.css"
import {useNavigate} from "react-router-dom";

interface LoginFormProps {
    title: string;
    type: 'log-in' | 'sign-up';
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
                            {type === 'log-in' ? "Log in" : "Sign up"}
                        </Button>
                        <div>
                            {/* Dummy element for using space */}
                        </div>
                        <Button
                            className="button-div"
                            view="outlined-info"
                            size="l"
                            type="submit"
                            onClick={() => navigate(type === 'log-in' ? "/sign-up" : "/log-in")}
                        >
                            {type === 'log-in' ? "Sign up" : "Log in"}
                        </Button>
                    </div>
                </div>
            </Card>
        </div>
    );
};

export default LogInForm;
