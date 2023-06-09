import { Link, useNavigate } from "react-router-dom";
import ToggleSwitch from "./toggleSwitch";
import { hide, save } from "../services/OutfitService";
import { useState, useEffect } from "react";
import { findAppUser } from "../services/authService";

function OutfitView({ duck, hat, shirt, pants, outfit, canDelete, error, profileView, loadOutfits }) {

    const navigate = useNavigate();
    const [result, setResult] = useState();
    //Bea's changes -- I also added the imports at the top
    const [user, setUser] = useState({});



    //Bea's changes
    useEffect(() => {
        findAppUser(outfit.userId)
            .then(setUser)
        console.log(outfit.userId);
    }, [outfit.userId]);

    function handleView() {
        navigate(`/forum/${outfit.outfitId}`);
    }

    function handleEdit() {
        navigate(`/dress-up-duck/edit/${outfit.outfitId}`)
    }

    function handleDelete() {
        hide(outfit.outfitId)
            .then(() => {loadOutfits();
                setResult("Success! Outfit deleted.")})
            .catch(() => setResult("Failure to delete outfit."));

    }

    function handleChange(posted) {
        const nextOutfit = { ...outfit }
        nextOutfit.posted = posted;
        save(nextOutfit)
            .then(() => setResult("Success! Status changed."))
            .catch(() => setResult("Failure to change status."));
    }

    return (
        <div>
            {profileView ?
                <div className="card m-1" style={{ width: "16.5rem" }}>
                    <div className="card-img-top">
                        <button onClick={handleEdit} style={{ border: "none", backgroundColor: "white", margin: "0 0 300px", padding: "10px" }}>
                            {duck && <img src={duck.duckImage} alt="duck" style={{ height: "300px", position: "absolute" }} />}
                            {hat && <img src={hat.clothingItemImage} alt="hat" style={{ height: "300px", position: "absolute" }} />}
                            {pants && <img src={pants.clothingItemImage} alt="pants" style={{ height: "300px", position: "absolute" }} />}
                            {shirt && <img src={shirt.clothingItemImage} alt="shirt" style={{ height: "300px", position: "absolute" }} />}
                        </button>
                    </div>
                    <div className="card-body">{<ToggleSwitch outfitId={outfit.outfitId} posted={outfit.posted} handleChange={handleChange} />}</div>
                    <div className="card-body"><button onClick={handleDelete} className="btn btn-danger mt-2">Delete</button></div>
                    {result && <p className="text-success">{result}</p>}

                </div>
                :
                
                <div style={{height: "350px", width: "275px", marginBottom: "50px"}}>
                    <p style={{margin: "0px 0px 0px 105px"}}>{user.username}</p>
                    <button onClick={handleView} style={{ border: "none", backgroundColor: "white", padding: "0px"}}>
                    
                        {outfit.duckId && <img src={duck.duckImage} alt="duck" style={{ height: "300px", position: "absolute" }} />}
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