import { Link } from "react-router-dom";

function ClothingItem({ item, canDelete, handleChange }) {

    let typeId = item.itemType + "Id";

    return (
        <div className="mb-2 d-flex justify-content-between">
            <div className="d-none">{item.itemId}</div>
            <div>
                <button onClick={handleChange} value={item.itemId} name={typeId} style={{ border: "none", backgroundColor: "white" }}>
                    <img src={item.clothingItemImage} alt="clothing-item" style={{ height: "100px", marginLeft: "10px" }} />
                </button>
            </div>
            <div className="d-none">{item.hidden}</div>
            <div>
                {canDelete && (
                    <Link to="/delete" className="btn btn-danger">Delete</Link>
                )}
            </div>
        </div>
    );
}

export default ClothingItem;