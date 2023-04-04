import { Link, useNavigate } from "react-router-dom";

function OutfitView({ duck, hat, shirt, pants, outfit, canDelete, error }) {

    const navigate = useNavigate();

    function handleView() {
        navigate(`/forum/${outfit.outfitId}`);
    }

    return (
        <div className="d-flex justify-content-between">
            <div>
                <button onClick={handleView} style={{ border: "none", backgroundColor: "white", margin: "0 175px 0 175px"}}>
                    <img src={duck.duckImage} alt="duck" style={{ height: "300px", position: "absolute" }} />
                    {hat && <img src={hat.clothingItemImage} alt="hat" style={{ height: "300px", position: "absolute" }} />}
                    {pants && <img src={pants.clothingItemImage} alt="pants" style={{ height: "300px", position: "absolute" }} />}
                    {shirt && <img src={shirt.clothingItemImage} alt="shirt" style={{ height: "300px", position: "absolute" }} />}
                </button>
            </div>

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