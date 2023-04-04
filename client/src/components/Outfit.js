import { findDuck } from "../services/DuckService";
import { findItem } from "../services/ClothingItemService";
import { useContext, useEffect, useState } from "react";
import AuthContext from "../contexts/AuthContext";
import OutfitPlay from "./OutfitPlay";
import OutfitView from "./OutfitView";

function Outfit({ outfit, canDelete, viewOutfit, handleView }) {

    const [duck, setDuck] = useState([]);
    const [hat, setHat] = useState();
    const [shirt, setShirt] = useState();
    const [pants, setPants] = useState();
    const [error, setError] = useState(false);
    const { user } = useContext(AuthContext);

    useEffect(() => {
        findDuck(outfit.duckId)
            .then(setDuck)
            .catch(() => setError(true));
        console.log(outfit.duckId);
    }, [outfit.duckId]);

    useEffect(() => {
        if (outfit.hatId) {


            findItem(outfit.hatId)
                .then(setHat)
                .catch(() => setError(true));
        }
        console.log("hatId", outfit.hatId);
    }, [outfit.hatId]);

    useEffect(() => {
        if (outfit.shirtId) {
            findItem(outfit.shirtId)
                .then(setShirt)
                .catch(() => setError(true));
        }
    }, [outfit.shirtId]);

    useEffect(() => {
        if (outfit.pantsId) {
            findItem(outfit.pantsId)
                .then(setPants)
                .catch(() => setError(true));
        }
    }, [outfit.pantsId]);

    return (<>{viewOutfit ? <OutfitView duck={duck} hat={hat} shirt={shirt} pants={pants}
                                outfit={outfit} canDelete={canDelete} error={error} handleView={handleView}/>
                            : <OutfitPlay duck={duck} hat={hat} shirt={shirt} pants={pants}
                                outfit={outfit} error={error} /> }</>);
}

export default Outfit;