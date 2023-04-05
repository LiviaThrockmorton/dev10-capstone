import { Link, useNavigate, useParams } from "react-router-dom";
import background from "./images/mallard_lake.jpg";
import { useState, useContext, useEffect } from "react";
import Outfit from "./Outfit";
import AuthContext from "../contexts/AuthContext";
import { findByUser } from "../services/OutfitService";

function Profile() {

    const navigate = useNavigate();
    const auth = useContext(AuthContext);
    const [outfits, setOutfits] = useState([]);
    const [error, setError] = useState();
    const canHide = auth.user;
    const canPost = auth.user;

    useEffect(() => {
        findByUser(auth.user.app_user_id)
            .then(setOutfits)
            .catch(() => setError(true));
        console.log(auth.user);
    }, [auth.user.app_user_id, navigate]);

    return (
        <div className="container">
            <h1 className="text-center mb-5">Profile</h1>
            <div className="row">
                <div className="col-6">
                    <h4 className="text-center">Your Outfits</h4>
                    <div className="d-flex flex-wrap">
                        {outfits.map(o => <Outfit key={o.outfitId} outfit={o} viewOutfit={true} canHide={canHide} canPost={canPost} />)}
                    </div>
                    {error && <p className="text-danger">Your outfits aren't here at the moment...</p>}
                </div>

                <div className="h-100 col-6 bg-image" style={{ backgroundImage: `url(${background})`, backgroundPosition: "center", backgroundSize: "cover" }}>
                    <div className="d-flex align-items-center flex-column pt-3 mt-5 mb-5" style={{ backgroundColor: "rgba(255,255,255, .75)" }}>
                        <Link to="/forum" className="btn btn-primary mb-4 mt-4">Forum</Link>
                        <Link to="/dress-up-duck" className="btn btn-primary mb-4">Dress Up!</Link>
                        <button className="btn btn-danger mb-5" onClick={() => auth.logout()}>Logout</button>
                    </div>
                    <div style={{ height: "800px" }}></div>
                </div>

            </div>
        </div>
    )
}

export default Profile;