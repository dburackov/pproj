import { Link } from "react-router-dom";
import React from "react";

export default function Navbar() {
    return (
        <nav className="navbar navbar-expand-lg navbar-light nav-underline">
            <ul className="navbar-nav">
                <li className="nav-item">
                    <Link className="nav-link" to={`/pet-profiles`}>Pet profiles</Link>
                </li>
                <li>
                    <Link className="nav-link" to={`/tags`}>Tags </Link>
                </li>
            </ul>
        </nav>
    );
}