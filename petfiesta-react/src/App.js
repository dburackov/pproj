import {
    createBrowserRouter,
    RouterProvider,
  } from "react-router-dom";
  import Root from './routes/root';
  import Tag from './routes/tags';
  import PetProfile from './routes/petProfiles';
  // import Users from './routes/users';
  import Signup from './routes/signup';
  import Signin from './routes/signin';
  import { useCookies } from "react-cookie";
  import { useEffect, useState } from "react";
  import { AppContext } from "./contexts/contexts";
  import { getUserById } from "./api/userService";
  import axios from "axios";
  
  export default function App() {
    const [cookies, setCookie, removeCookie] = useCookies(['id', 'token']);
    const [isAuthenticated, setIsAuthenticated] = useState(cookies.token !== undefined);
    const [user, setUser] = useState({});
  
    useEffect(() => {
      if (cookies.userId !== undefined) {
        getUserById(cookies.userId).then(user => setUser(user));
      }
  
      if (cookies.token !== undefined) {
        axios.defaults.headers.common['Authorization'] = `Bearer ${cookies.token}`;
      }
      else {
        axios.defaults.headers.common['Authorization'] = '';
      }
    }, [isAuthenticated]);
  
    const state = {
      setCookie,
      removeCookie,
      isAuthenticated,
      setIsAuthenticated,
      user,
      setUser
    };
  
    const authenticatedRouter = createBrowserRouter([
      {
        path: "/",
        element: <Root/>,
        // errorElement: <NotFound />,
        children: [
          // {
          //   path: "/users",
          //   element: <Users />
          // },
          {
            path: "/tags",
            element: <Tag />
          },
          {
            path: "/pet-profile",
            element: <PetProfile />
          }
        ]
      }
    ]);

    const anonymousRouter = createBrowserRouter([
      {
        path: "/",
        element: <Root/>,
        // errorElement: <NotFound />,
        children: [
          // {
          //   path: "/users",
          //   element: <Users />
          // },
          {
            path: "/signup",
            element: <Signup />
          },
          {
            path: "/signin",
            element: <Signin />
          }
        ]
      }
    ]);

  
    return (
      <AppContext.Provider value={state}>
        <RouterProvider router={isAuthenticated ? authenticatedRouter : anonymousRouter} />
      </AppContext.Provider>
    );
  }