import { Link } from "react-router-dom";

function Outfit({outfit, canDelete}) {

    return (
        <div className="mb-2 d-flex justify-content-between">
            <div className="d-none">{outfit.outfitId}</div>
            <div className="d-none">{outfit.duckId}</div>
            <div className="d-none">{outfit.dateCreated}</div>
            <div className="d-none">{outfit.shirtId}</div>
            <div className="d-none">{outfit.pantsId}</div>
            <div className="d-none">{outfit.hatId}</div>
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