import {
    createBrowserRouter,
    RouterProvider,
  } from "react-router-dom";
import Root from './routes/root';
import Tags, {loader as tagsLoader } from './routes/tags';
import PetProfiles, {loader as petProfileLoader} from './routes/petProfiles';
import Signup from './routes/signup';
import Signin from './routes/signin';
import { useCookies } from "react-cookie";
import { useEffect, useState } from "react";
import { AppContext } from "./contexts/contexts";
import { getUserById } from "./api/userService";
import axios from "axios";
import {PetProfileCreate} from "./routes/petProfileCreate";
import {PetProfileUpdate} from "./routes/petProfileUpdate";
import {TagCreate} from "./routes/tagCreate";
import {TagUpdate} from "./routes/tagUpdate";
import "./App.css"

export default function App() {
    const [cookies, setCookie, removeCookie] = useCookies(['userId', 'token']);
    const [isAuthenticated, setIsAuthenticated] = useState(cookies.token !== undefined);
    const [user, setUser] = useState({});

    useEffect(() => {
        if (cookies.userId !== undefined) {
            getUserById(cookies.userId).then(user => setUser(user));
        }
  
        if (cookies.token !== undefined) {
            axios.defaults.headers.common['Authorization'] = `Bearer ${cookies.token}`;
        } else {
            axios.defaults.headers.common['Authorization'] = '';
        }
    }, [isAuthenticated]);

    const state = {
        cookies,
        setCookie,
        removeCookie,
        isAuthenticated,
        setIsAuthenticated,
        user,
        setUser
    };

    const authenticatedRouter = createBrowserRouter([{
        path: "/",
        element: <Root/>,
        errorElement: <App />,
        children: [
            {
                path: "/tags",
                element: <Tags />
            },
            {
                path: "/tags/create",
                element: <TagCreate/>
            },
            {
                path: "/tags/update/:tagId",
                element: <TagUpdate/>,
                loader: tagsLoader
            },
            {
                path: "/pet-profiles",
                element: <PetProfiles />
            },
            {
                path: "/pet-profiles/create",
                element: <PetProfileCreate/>
            },
            {
                path: "/pet-profiles/update/:petProfileId",
                element: <PetProfileUpdate/>,
                loader: petProfileLoader
            }
        ]
    }]);

    const anonymousRouter = createBrowserRouter([{
        path: "/",
        element: <Root/>,
        errorElement: <App />,
        children: [
            {
                path: "/signup",
                element: <Signup />
            },
            {
                path: "/signin",
                element: <Signin />
            }
        ]
      }]);

    return (
        <AppContext.Provider value={state}>
            <RouterProvider router={isAuthenticated ? authenticatedRouter : anonymousRouter} />
        </AppContext.Provider>
    );
}