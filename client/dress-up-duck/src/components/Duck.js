import { Link } from "react-router-dom";

function Duck({ duck, canDelete, handleChange }) {

    return (
        <div className="mb-2 d-flex justify-content-between">
            <div className="d-none">{duck.duckId}</div>
            <div>
                <button onClick={handleChange} value={duck.duckId} name="duck">
                    <img src={duck.duckImage} alt="duck" style={{ height: "100px", marginLeft: "10px" }} />
                </button>
            </div>
            <div className="d-none">{duck.hidden}</div>
            <div>
                {canDelete && (
                    <Link to="/delete" className="btn btn-danger">Delete</Link>
                )}
            </div>
        </div>
    );
}

export default Duck;