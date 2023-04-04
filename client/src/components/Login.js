import background from "./images/sink_ducklings.jpg";
import { Link, useNavigate } from "react-router-dom";
import React, { useState, useContext } from "react";
import AuthContext from "../context/AuthContext";

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [errors, setErrors] = useState([]);
    const auth = useContext(AuthContext);
    const navigate = useNavigate();

    const handleSubmit = async (evt) => {
        evt.preventDefault();
        
        const response = await fetch("http://localhost:8080/authenticate", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ username, password }),
        });

        if (response.status === 200) {
            const {jwt_token} = await response.json();
            console.log(jwt_token);
            auth.login(jwt_token);
            navigate("/");
            navigate("/");
        } else if (response.status === 403) {
            setErrors(["Login attempt failed, try again"]);
        } else {
            setErrors(["Unknown error."]);
        }
    }

    return (
        <div className="container">
            <div className="flex-row w-50">
                <h1 className="text-center">Log In</h1>
                <h5 className="text-center">Don't have an account? Create one {' '}
                    <Link to="/create-account" className="btn btn-outline-secondary">here</Link>
                </h5>

                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="username">Username:</label>
                        <input type="text" onChange={(event) => setUsername(event.target.value)} className="form-control mb-4" id="username" />
                    </div>

                    <div className="form-group">
                        <label htmlFor="password">Password:</label>
                        <input type="password" onChange={(event) => setPassword(event.target.value)} className="form-control mb-4" id="password" />
                    </div>

                    <button type="submit" className="btn btn-primary">Submit</button>
                </form>

                {errors && <p className="text-danger">{errors}</p>}
            </div>


            <div className="flex-row-reverse bg-image w-50" style={{ backgroundImage: `url(${background})` }}>
                <div className="h-100 w-100"></div>
            </div>
        </div>
    )
}

export default Login;