import background from "./images/brown_duckling.jpg";
import { useContext, useState, useLayoutEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { authenticate } from "../services/authService";
import AuthContext from "../contexts/AuthContext";
import { gsap } from "gsap";

function Login() {

    const [credentials, setCredentials] = useState({ username: "", password: "" });
    const [hasError, setHasError] = useState(false);
    const { login } = useContext(AuthContext);
    const navigate = useNavigate();

    //ANIMATION
    useLayoutEffect(() => {
        let ctx = gsap.context(() => {
            gsap.fromTo(".brownDuckling", { y: 300 }, { duration: 2, y: 0, ease: "bounce" });
            return () => ctx.revert();});
    }, [navigate]);

    function handleChange(evt) {
        const next = { ...credentials };
        next[evt.target.name] = evt.target.value;
        setCredentials(next);
    }

    function handleSubmit(evt) {
        evt.preventDefault();
        authenticate(credentials)
            .then(user => {
                login(user);
                navigate("/profile");
            })
            .catch(() => setHasError(true));
    }

    return (
        <div className="container">
            <div className="row">
                <div className="col-6">
                    <h1 className="text-center">Log In</h1>
                    <h5 className="text-center">Don't have an account? Create one {' '}
                        <Link to="/create-account" className="btn btn-outline-secondary">here</Link>
                    </h5>

                    <form onSubmit={handleSubmit}>
                        <div className="form-group">
                            <label htmlFor="username">Username:</label>
                            <input type="text" onChange={handleChange} className="form-control mb-4" id="username" name="username"
                                value={credentials.username} />
                        </div>

                        <div className="form-group">
                            <label htmlFor="password">Password:</label>
                            <input type="password" onChange={handleChange} className="form-control mb-4" id="password" name="password" value={credentials.password} />
                        </div>

                        <button type="submit" className="btn btn-primary">Submit</button>
                    </form>

                    {hasError && <p className="text-danger d-none">Login attempt failed, try again</p>}
                </div>

                <div className="h-100 col-6 bg-image brownDuckling" style={{ backgroundImage: `url(${background})`, backgroundPosition: "center", backgroundSize: "cover" }}>
                    <div style={{ height: "800px" }}></div>
                </div>
            </div>
        </div>
    )
}

export default Login;