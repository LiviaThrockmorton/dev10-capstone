import { Link, useNavigate } from "react-router-dom";
import ToggleSwitch from "./toggleSwitch";
import { hide, save } from "../services/OutfitService";
import { useState } from "react";

function OutfitView({ duck, hat, shirt, pants, outfit, canDelete, error, profileView }) {

    const navigate = useNavigate();
    const [result, setResult] = useState();

    function handleView() {
        navigate(`/forum/${outfit.outfitId}`);
    }

    function handleEdit() {
        navigate(`/dress-up-duck/edit/${outfit.outfitId}`)
    }

    function handleDelete() {
        hide(outfit.outfitId)
            .then(() => setResult("Success! Outfit deleted."))
            .catch(() => setResult("Failure to delete outfit."));

    }

    function handleChange(hidden) {
        const nextOutfit = {...outfit}
        nextOutfit.hidden = hidden;
        save (nextOutfit)
        .then(() => setResult("Success! Outfit hidden."))
        .catch(() => setResult("Failure to hide outfit."));
    }

    return (
        <div>
            {profileView ?
                <div className="card m-1" style={{ width: "16.5rem" }}>
                    
                    
                            <div className="card-img-top">
                                <button onClick={handleEdit} style={{ border: "none", backgroundColor: "white", margin: "0 0 300px", padding: "10px" }}>
                                    <img src={duck.duckImage} alt="duck" style={{ height: "300px", position: "absolute" }} />
                                    {hat && <img src={hat.clothingItemImage} alt="hat" style={{ height: "300px", position: "absolute" }} />}
                                    {pants && <img src={pants.clothingItemImage} alt="pants" style={{ height: "300px", position: "absolute" }} />}
                                    {shirt && <img src={shirt.clothingItemImage} alt="shirt" style={{ height: "300px", position: "absolute" }} />}
                                </button>
                            </div>
                            <div className="card-body">{<ToggleSwitch outfitId={outfit.outfitId} hidden={outfit.hidden} handleChange={handleChange} />}</div>
                            <div className="card-body"><button onClick={handleDelete} className="btn btn-danger mt-2">Delete</button></div>
                            {result && <p className="text-success">{result}</p>}
                    
                    
                </div>
                :
                <div className="d-flex flex-wrap">
                    <button onClick={handleView} style={{ border: "none", backgroundColor: "white", margin: "0 120px 300px", padding: "10px" }}>
                        <img src={duck.duckImage} alt="duck" style={{ height: "300px", position: "absolute" }} />
                        {hat && <img src={hat.clothingItemImage} alt="hat" style={{ height: "300px", position: "absolute" }} />}
                        {pants && <img src={pants.clothingItemImage} alt="pants" style={{ height: "300px", position: "absolute" }} />}
                        {shirt && <img src={shirt.clothingItemImage} alt="shirt" style={{ height: "300px", position: "absolute" }} />}
                    </button>
                </div>
            }




            <div>{error && <p className="text-danger">This duck doesn't want to wear clothes</p>}</div>
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

export default OutfitView;