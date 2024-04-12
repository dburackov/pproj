import { Link } from "react-router-dom";
import React from "react";

export default function AuthNavbar() {
    return (
        <nav className="navbar navbar-expand-lg navbar-light nav-underline">
            <ul className="navbar-nav">
                <li className="nav-item">
                    <Link className="nav-link" to={`/signin`}>sign in</Link>
                </li>
                <li className="nav-item">
                    <Link className="nav-link" to={`/signup`}>sign up</Link>
                </li>
            </ul>
        </nav>
    );
}