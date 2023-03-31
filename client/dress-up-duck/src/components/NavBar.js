import { Link } from "react-router-dom";
import { useContext } from "react";
import AuthContext from "../context/AuthContext";

function NavBar() {

    const auth = useContext(AuthContext);
    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark mb-5">
            <div className="container-fluid">
            <div className="navbar-brand">Dress-Up Duck</div>

                {auth.user && (
                    <div>
                        <div className="navbar-brand">Welcome</div>
                        <p className="nav-item">{auth.user.username}</p>
                        <button onClick={() => auth.logout()}>Logout</button>
                    </div>
                )}
                
                <div className="navbar-collapse" id="navbarColor02">
                    <ul className="navbar-nav me-auto">

                        {auth.user ? (
                            <li className="nav-item"><Link to="/profile" className="nav-link">Profile</Link></li>
                        ) : (
                            <li className="nav-item"><Link to="/login" className="nav-link">Login</Link></li>
                        )}
                        
                        <li className="nav-item"><Link to="/forum" className="nav-link">Forum</Link></li>

                        <li className="nav-item"><Link to="/dress-up-duck" className="nav-link">New Outfit</Link></li>

                        <li className="nav-item"><Link to="/" className="nav-link">Home</Link></li>

                    </ul>
                </div>
            </div>
        </nav>  
    )
}

export default NavBar;