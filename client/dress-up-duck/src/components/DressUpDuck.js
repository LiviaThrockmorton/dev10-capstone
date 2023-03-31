import { useContext, useEffect, useState } from "react";
import {useNavigate} from "react-router-dom";
import AuthContext from "../context/AuthContext";
import { findAll } from "../services/DuckService";
import Duck from "./Duck";

function DressUpDuck({handleDelete}) {

    const [ducks, setDucks] = useState([]);
    const navigate = useNavigate();
    const auth = useContext(AuthContext);
    const canDelete = auth.user && auth.user.hasRole("ADMIN");

    useEffect(() => {
        findAll()
            .then(setDucks)
            .then(document.getElementById("duckError").classList.add("d-none"))
            .catch(error => document.getElementById("duckError").classList.remove("d-none"));
    }, [navigate]);

    return (
        <div className="container">
            <div className="row">
                <div className="col-6">
                    <div className="row mb-2">
                        <div className="col-1 mt-4">Ducks</div>
                        <div className="col d-flex flex-row">
                            {ducks.map(d => <Duck key={d.id} duck={d} handleDelete={handleDelete} canDelete={canDelete} />)}
                        </div>
                        <p id="duckError" className="col text-danger d-none">The ducks got loose!</p>
                    </div>
                    

                </div>

                <div className="col-6">
                    
                </div>
            </div>
        </div>
    )
}

export default DressUpDuck;

// /api/item/{itemtype}