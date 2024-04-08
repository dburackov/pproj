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
            <input
                placeholder="Name"
                onChange={e => setLogin(e.target.value)}
            />
            <button
                onClick={signupButtonOnClick}
            >
                Sign up
            </button>
        </section>
    );

    async function signupButtonOnClick() {
        const data = await signUp(name, email, password);
        appContext.setCookie('userId', data.id);
        appContext.setCookie('token', data.token);
        appContext.setIsAuthenticated(true);
        navigate('/');
    }
}