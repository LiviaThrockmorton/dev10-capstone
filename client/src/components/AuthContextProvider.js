import { useEffect, useState } from "react";
import AuthContext from "../contexts/AuthContext";
import { refresh } from "../services/authService";

function AuthContextProvider({ children }) {

    const [user, setUser] = useState();

    useEffect(() => {
        if (localStorage.getItem("duckToken")) {
            refresh().then(login).catch(logout);
        }
    }, [])

    function login(userArg) {
        setUser(userArg);
        localStorage.setItem("duckToken", userArg.jwt);
    }

    function logout() {
        setUser();
        localStorage.removeItem("duckToken");
    }

    const auth = {
        user,
        login,
        logout
    };

    return (
        <AuthContext.Provider value={auth}>
            {children}
        </AuthContext.Provider>
    )
}

export default AuthContextProvider;