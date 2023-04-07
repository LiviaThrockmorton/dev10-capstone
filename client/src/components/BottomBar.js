import { Link } from "react-router-dom";
import { useContext } from "react";
import AuthContext from "../contexts/AuthContext";
import { findAppUser } from "../services/authService";

function BottomNav() {

    const auth = useContext(AuthContext);
    return (

        <nav className="navbar navbar-expand-lg navbar-dark bg-dark mb-5 sticky-top">
            <div className="container-fluid">
                <div className="navbar-brand">Dress-Up Duck</div>


 
            </div>
        </nav>




    )
}

export default BottomNav;