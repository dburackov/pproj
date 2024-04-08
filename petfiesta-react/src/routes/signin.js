import React from 'react'
import ReactDOM from 'react-dom'
import { useContext, useState } from 'react';
import { signIn } from '../api/authService';
import { AppContext } from '../contexts/contexts';
import { useNavigate } from 'react-router-dom';

export default function Signin() {
    const appContext = useContext(AppContext);
    
    const navigate = useNavigate();
    
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    return (
        <section>
            <input
                placeholder="Email"
                type="email"
                onChange={e => setEmail(e.target.value)}
            />
            <input
                placeholder="Password"
                type="password"
                onChange={e => setPassword(e.target.value)}
            />
            <button
                onClick={signinButtonOnClick}
            >
                Sign in
            </button>
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