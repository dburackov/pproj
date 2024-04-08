import { Link } from "react-router-dom";

export default function Navbar() {
    return (
        <nav>
            <ul>
                <li>
                    <Link to={`/signin`}>sign in</Link>
                </li>
                <li>
                    <Link to={`/signup`}>sign up</Link>
                </li>
                <li>
                    <Link to={`/pet-profiles`}>Pet profiles</Link>
                </li>
                <li>
                    <Link to={`/tags`}>Tags </Link>
                </li>
            </ul>
        </nav>
    );
}