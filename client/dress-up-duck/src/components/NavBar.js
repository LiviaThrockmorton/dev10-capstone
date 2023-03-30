import { Link } from "react-router-dom";

function NavBar() {
    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark mb-5">
            <div className="container-fluid">
                <div className="navbar-brand">Dress-Up Duck</div>
                <div className="navbar-collapse" id="navbarColor02">
                    <ul className="navbar-nav me-auto">

                        <li className="nav-item"><Link to="/login" className="nav-link">Login</Link></li>

                        <li className="nav-item"><Link to="/profile" className="nav-link">Profile</Link></li>

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