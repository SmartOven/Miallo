import React, {ChangeEvent, KeyboardEvent, useState} from 'react';
import {Button, Card, TextInput} from '@gravity-ui/uikit';

interface LoginFormProps {
    title: string;
    buttonText: string;
    onSubmit: (login: string, password: string) => void;
}

const LoginForm: React.FC<LoginFormProps> = ({title, buttonText, onSubmit}) => {
    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');

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
        <div style={{width: 'fit-content', height: 'fit-content'}}>
            <Card className="card-stories">
                <div className="card-content-stories" style={{margin: '1em'}}>
                    <div className="reg-div" style={{marginRight: '80px'}}>
                        <h1>{title}</h1>
                    </div>
                    <div className="reg-div" style={{marginBottom: '8px'}}>
                        <TextInput
                            id="login"
                            name="login"
                            placeholder="LoginForm"
                            size="l"
                            value={login}
                            onChange={onChange}
                            onKeyDown={onSubmitByKeyDown}
                        />
                    </div>
                    <div className="reg-div" style={{marginBottom: '8px'}}>
                        <TextInput
                            id="password"
                            name="password"
                            placeholder="Password"
                            size="l"
                            type="password"
                            value={password}
                            onChange={onChange}
                            onKeyDown={onSubmitByKeyDown}
                        />
                    </div>
                    <div className="reg-div">
                        <Button
                            view="action"
                            size="l"
                            type="submit"
                            onClick={() => onSubmit(login, password)}
                        >
                            {buttonText}
                        </Button>
                    </div>
                </div>
            </Card>
        </div>
    );
};

export default LoginForm;
