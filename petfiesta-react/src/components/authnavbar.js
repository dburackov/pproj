import { Link } from "react-router-dom";

export default function AuthNavbar() {
    return (
        <nav>
            <ul>
                <li>
                    <Link to={`/signin`}>sign in</Link>
                </li>
                <li>
                    <Link to={`/signup`}>sign up</Link>
                </li>
            </ul>
        </nav>
    );
}