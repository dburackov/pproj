import React, { useContext, useState } from 'react';
import { signIn } from '../api/authService';
import { AppContext } from '../contexts/contexts';
import { useNavigate } from 'react-router-dom';

export default function Signin() {
    const appContext = useContext(AppContext);
    
    const navigate = useNavigate();
    
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    return (
        <section className="main-content auth">
            <input
                placeholder="Email"
                type="email"
                className="form-control input-margin"
                onChange={e => setEmail(e.target.value)}
            />
            <input
                placeholder="Password"
                type="password"
                className="form-control input-margin"
                onChange={e => setPassword(e.target.value)}
            />
            <button className="btn btn-success" onClick={signinButtonOnClick}>Sign in</button>
        </section>
    );

    async function signinButtonOnClick() {
        const data = await signIn(email, password);
        appContext.setCookie('userId', data.id);
        appContext.setCookie('token', data.token);
        appContext.setIsAuthenticated(true);
        navigate('/');
    }
}