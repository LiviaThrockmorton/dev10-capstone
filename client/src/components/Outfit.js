import { Link } from "react-router-dom";
import { findDuck } from "../services/DuckService";
import { findItem } from "../services/ClothingItemService";
import { useContext, useEffect, useState } from "react";
import AuthContext from "../contexts/AuthContext";

function Outfit({ outfit, canDelete }) {

    const [duck, setDuck] = useState([]);
    const [hat, setHat] = useState();
    const [shirt, setShirt] = useState();
    const [pants, setPants] = useState();
    const [error, setError] = useState(false);
    const { user } = useContext(AuthContext);

    useEffect(() => {
        findDuck(outfit.duckId)
            .then(setDuck)
            .catch(() => setError(true));
        console.log(outfit.duckId);
    }, [outfit.duckId]);

    useEffect(() => {
        if (outfit.hatId) {


            findItem(outfit.hatId)
                .then(setHat)
                .catch(() => setError(true));
        }
        console.log("hatId", outfit.hatId);
    }, [outfit.hatId]);

    useEffect(() => {
        if (outfit.shirtId) {
            findItem(outfit.shirtId)
                .then(setShirt)
                .catch(() => setError(true));
        }
    }, [outfit.shirtId]);

    useEffect(() => {
        if (outfit.pantsId) {
            findItem(outfit.pantsId)
                .then(setPants)
                .catch(() => setError(true));
        }
    }, [outfit.pantsId]);

    return (
        <div className="mb-2 d-flex justify-content-between">
            <div>
                <img src={duck.duckImage} alt="duck" style={{ height: "800px", position: "absolute" }} />
                {hat && <img src={hat.clothingItemImage} alt="hat" style={{ height: "800px", position: "absolute" }} />}
                {pants && <img src={pants.clothingItemImage} alt="pants" style={{ height: "800px", position: "absolute" }} />}
                {shirt && <img src={shirt.clothingItemImage} alt="shirt" style={{ height: "800px", position: "absolute" }} />}
            </div>

            <div>{error && <p className="col mt-4 text-danger d-none">This duck doesn't want to wear clothes</p>}</div>
            <div className="d-none">{outfit.outfitId}</div>
            <div className="d-none">{outfit.dateCreated}</div>
            <div className="d-none">{outfit.posted}</div>
            <div className="d-none">{outfit.hidden}</div>
            <div>
                {canDelete && (
                    <Link to="/delete" className="btn btn-danger">Delete</Link>
                )}
            </div>
        </div>
    );
}

export default Outfit;

//