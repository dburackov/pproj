import { useContext, useState } from 'react';
import { signUp } from '../api/authService';
import { AppContext } from '../contexts/contexts';
import { useNavigate } from 'react-router-dom';

export default function Signup() {
    const appContext = useContext(AppContext);

    const navigate = useNavigate();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [login, setLogin] = useState('');

    return (
        <section className="main-content auth">
            <input
                placeholder="Login"
                className="form-control input-margin"
                onChange={e => setLogin(e.target.value)}
            />
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
            <button className="btn btn-success" onClick={signupButtonOnClick}>Sign up</button>
        </section>
    );

    async function signupButtonOnClick() {
        const data = await signUp(login, email, password);
        appContext.setCookie('userId', data.id);
        appContext.setCookie('token', data.token);
        appContext.setIsAuthenticated(true);
        navigate('/');
    }
}