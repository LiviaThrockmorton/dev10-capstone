import { useContext, useEffect, useState, useLayoutEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import AuthContext from "../contexts/AuthContext";
import { findAll } from "../services/DuckService";
import Duck from "./Duck";
import ClothingItem from "./ClothingItem";
import { findByType } from "../services/ClothingItemService";
import { findById, save } from "../services/OutfitService";
import Outfit from "./Outfit";
import yellowDuck from "./images/yellow-duck-no-arm.svg"
import duckArm from "./images/yellow-duck-arm.svg"
import { gsap } from "gsap";

const baseOutfit = { outfitId: "", userId: "", duckId: "", shirtId: "", pantsId: "", hatId: "", dateCreated: "", posted: "false", hidden: "false" }

function DressUpDuck({ handleDelete }) {

    const [ducks, setDucks] = useState([]);
    const [hats, setHats] = useState([]);
    const [shirts, setShirts] = useState([]);
    const [pants, setPants] = useState([]);
    const [outfit, setOutfit] = useState([]);
    const { outfitId } = useParams();
    const navigate = useNavigate();
    const auth = useContext(AuthContext);
    const canDelete = auth.user && auth.user.hasAnyAuthority("ADMIN");
    const [error, setError] = useState(false);
    const [saveResult, setSaveResult] = useState();

    //ANIMATION
    useLayoutEffect(() => {
        let ctx = gsap.context(() => {
            gsap.to(".arm", {duration: .25, rotation: 40});
            gsap.to(".arm", {duration: .25, rotation: 20, delay: .25});
            gsap.to(".arm", {duration: .25, rotation: 40, delay: .5});
            gsap.to(".arm", {duration: .5, rotation: 0, delay: .75});
            return () => ctx.revert();});
    }, [navigate]);

    //GET DUCK AND CLOTHING ITEMS
    useEffect(() => {
        findAll()
            .then(setDucks)
            .catch(() => setError(true));
    }, [navigate]);

    useEffect(() => {
        findByType("hat")
            .then(setHats)
            .catch(() => setError(true));
    }, [navigate]);

    useEffect(() => {
        findByType("shirt")
            .then(setShirts)
            .catch(() => setError(true));
    }, [navigate]);

    useEffect(() => {
        findByType("pants")
            .then(setPants)
            .catch(() => setError(true));
    }, [navigate]);

    //OUTFIT FORM STUFF
    useEffect(() => {
        if (outfitId) {
            findById(outfitId)
                .then(setOutfit)
                .catch(() => navigate("/profile"));
        } else {
            setOutfit(baseOutfit);
        }
    }, [outfitId, navigate]);

    function handleChange(evt) {
        const btn = evt.target.parentNode;
        const nextOutfit = { ...outfit };
        nextOutfit[btn.name] = btn.value;
        setOutfit(nextOutfit);
        console.log(nextOutfit);
    }

    function handleSave(evt) {
        evt.preventDefault();


        if (auth.user) {
            const nextOutfit = { ...outfit };
            nextOutfit.userId = auth.user.app_user_id;

            save(nextOutfit)
                .then(() => setSaveResult("Success! Outfit saved."))
                .catch(() => setSaveResult("Failure to save outfit."))
        } else {
            navigate("/login")
        }

    }

    return (
        <div className="container">
            <div className="row">
                <div className="col-6">
                    <div className="row mb-2">
                        <div className="col-1 mt-4">Ducks</div>
                        <div className="col d-flex flex-row">
                            {ducks.map(d => <Duck key={d.duckId} duck={d} handleChange={handleChange} handleDelete={handleDelete} canDelete={canDelete} />)}
                        </div>
                        {error && <p className="col mt-4 text-danger">The ducks got loose!</p>}
                    </div>

                    <div className="row mb-2">
                        <div className="col-1 mt-4">Hats</div>
                        <div className="col d-flex flex-row" id="hat" name="hat">
                            {hats.map(h => <ClothingItem key={h.itemId} item={h} handleChange={handleChange} handleDelete={handleDelete} canDelete={canDelete} />)}
                        </div>
                        {error && <p className="col mt-4 text-danger">The ducks ate the hats!</p>}
                    </div>

                    <div className="row mb-2">
                        <div className="col-1 mt-4">Shirts</div>
                        <div className="col d-flex flex-row">
                            {shirts.map(s => <ClothingItem key={s.itemId} item={s} handleChange={handleChange} handleDelete={handleDelete} canDelete={canDelete} />)}
                        </div>
                        {error && <p className="col mt-4 text-danger">The shirts fell in the duck pond!</p>}
                    </div>

                    <div className="row mb-2">
                        <div className="col-1 mt-4">Pants</div>
                        <div className="col d-flex flex-row">
                            {pants.map(p => <ClothingItem key={p.itemId} item={p} handleChange={handleChange} handleDelete={handleDelete} canDelete={canDelete} />)}
                        </div>
                        {error && <p className="col mt-4 text-danger">How would a duck even wear pants?</p>}
                    </div>

                    <div className="row mb-2">
                        <button className="btn btn-primary" onClick={handleSave}>Save Outfit</button>
                    </div>
                    {saveResult && <p className="col mt-4">{saveResult}</p>}
                </div>

                <div className="col-6">
                    <div style={{ width: "800px", height: "1000px" }}>
                        <img src={yellowDuck} className="duck-no-arm" alt="duck" style={{ height: "600px", position: "absolute" }} />
                        <img src={duckArm} className="arm" alt="duck" style={{ height: "600px", position: "absolute" }} />
                        {<Outfit key={outfit.outfitId} outfit={outfit} viewOutfit={false} viewHome={false} profileView={false} />}
                    </div>
                </div>
            </div>
        </div>
    )
}

export default DressUpDuck;