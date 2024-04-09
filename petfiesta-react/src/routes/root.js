import { Outlet } from "react-router-dom";
import Navbar from "../components/navbar";
import {useContext} from "react";
import {AppContext} from "../contexts/contexts";
import AuthNavbar from "../components/authnavbar";

export default function Root() {
    const appContext = useContext(AppContext);

    return (
        <main>
            { appContext.isAuthenticated && <Navbar />}
            { !appContext.isAuthenticated && <AuthNavbar />}
            <Outlet />
        </main>
    );
}