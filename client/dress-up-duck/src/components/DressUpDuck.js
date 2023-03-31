import { useContext, useEffect, useState } from "react";
import {useNavigate, useParams} from "react-router-dom";
import AuthContext from "../context/AuthContext";
import { findAll } from "../services/DuckService";
import Duck from "./Duck";
import ClothingItem from "./ClothingItem";
import { findByType } from "../services/ClothingItemService";
import { findById } from "../services/OutfitService";

const baseOutfit = { outfitId: "", appUserId: "", duckId: 1, shirtId: "", pantsId: "", hatId: "", dateCreated: "", posted: "", hidden: ""}

function DressUpDuck({handleDelete}) {

    const [ducks, setDucks] = useState([]);
    const [hats, setHats] = useState([]);
    const [shirts, setShirts] = useState([]);
    const [pants, setPants] = useState([]);
    const [outfit, setOutfit] = useState(baseOutfit);
    const {outfitId} = useParams();
    const navigate = useNavigate();
    const auth = useContext(AuthContext);
    const canDelete = auth.user && auth.user.hasRole("ADMIN");

//DISPLAY DUCK AND CLOTHING ITEMS
    useEffect(() => {
        findAll()
            .then(setDucks)
            .then(document.getElementById("duckError").classList.add("d-none"))
            .catch(error => document.getElementById("duckError").classList.remove("d-none"));
    }, [navigate]);

    useEffect(() => {
        findByType("hat")
            .then(setHats)
            .then(document.getElementById("hatError").classList.add("d-none"))
            .catch(error => document.getElementById("hatError").classList.remove("d-none"));
    }, [navigate]);

    useEffect(() => {
        findByType("shirt")
            .then(setShirts)
            .then(document.getElementById("shirtError").classList.add("d-none"))
            .catch(error => document.getElementById("shirtError").classList.remove("d-none"));
    }, [navigate]);

    useEffect(() => {
        findByType("pants")
            .then(setPants)
            .then(document.getElementById("pantsError").classList.add("d-none"))
            .catch(error => document.getElementById("pantsError").classList.remove("d-none"));
    }, [navigate]);

//OUTFIT FORM STUFF
    useEffect(() => {
        if (outfitId) {
            findById(outfitId)
                .then(setOutfit)
                .catch(() => navigate("/profile"));
        }
    }, [outfitId, navigate]);

    function handleChange(evt) {
        const nextOutfit = { ...outfit };
        nextOutfit[evt.target.name] = evt.target.value;
        setOutfit(nextOutfit);
    }

    function handleSave(evt) {
        evt.preventDefault();
        const nextOutfit = { ...outfit };

        //save
    }

    return (
        <div className="container">
            <div className="row">
                <div className="col-6">
                    <div className="row mb-2">
                        <div className="col-1 mt-4">Ducks</div>
                        <div className="col d-flex flex-row">
                            {ducks.map(d => <Duck key={d.id} duck={d} handleDelete={handleDelete} canDelete={canDelete} />)}
                        </div>
                        <p id="duckError" className="col mt-4 text-danger d-none">The ducks got loose!</p>
                    </div>

                    <div className="row mb-2">
                        <div className="col-1 mt-4">Hats</div>
                        <div className="col d-flex flex-row">
                            {hats.map(h => <ClothingItem key={h.id} item={h} handleDelete={handleDelete} canDelete={canDelete} />)}
                        </div>
                        <p id="hatError" className="col mt-4 text-danger d-none">The ducks ate the hats!</p>
                    </div>
                    
                    <div className="row mb-2">
                        <div className="col-1 mt-4">Shirts</div>
                        <div className="col d-flex flex-row">
                            {shirts.map(s => <ClothingItem key={s.id} item={s} handleDelete={handleDelete} canDelete={canDelete} />)}
                        </div>
                        <p id="shirtError" className="col mt-4 text-danger d-none">The shirts fell in the duck pond!</p>
                    </div>

                    <div className="row mb-2">
                        <div className="col-1 mt-4">Pants</div>
                        <div className="col d-flex flex-row">
                            {pants.map(p => <ClothingItem key={p.id} item={p} handleDelete={handleDelete} canDelete={canDelete} />)}
                        </div>
                        <p id="pantsError" className="col mt-4 text-danger d-none">How would a duck even wear pants?</p>
                    </div>
                </div>

                <div className="col-6">
                    
                </div>
            </div>
        </div>
    )
}

export default DressUpDuck;