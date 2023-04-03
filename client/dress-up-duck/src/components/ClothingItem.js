import { Link } from "react-router-dom";

function ClothingItem({item, canDelete}) {

    return (
        <div className="mb-2 d-flex justify-content-between">
            <div className="d-none">{item.itemId}</div>
            <div><img src={item.itemImage} alt="clothing-item" style={{height: "100px", marginLeft: "10px"}}/></div>
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