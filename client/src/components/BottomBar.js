import { Link } from "react-router-dom";
import { useContext } from "react";
import AuthContext from "../contexts/AuthContext";
import { findAppUser } from "../services/authService";

function BottomNav() {

    const auth = useContext(AuthContext);
    return (

        <nav className="navbar navbar-dark bg-dark ">
            <div className="">

                <div className="row">
                    <div className="col">
                        © What the Ducks 2023 
                    </div>
                    <div className="col">Terms of Use</div>
                    <div className="col">
                        Privacy Policy</div>
                </div>

            </div>
        </nav>




    )
}

export default BottomNav;