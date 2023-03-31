import { Link } from "react-router-dom";

function Duck({duck, canDelete}) {

    return (
        <div className="row mb-2">
            <div className="d-none">{duck.duckId}</div>
            <div className="col">{duck.duckImage}</div>
            <div className="d-none">{duck.hidden}</div>
            <div className="col">
                {canDelete && (
                    <Link to="/delete" className="btn btn-danger">Delete</Link>
                )}
            </div>
        </div>
    );
}

export default Duck;