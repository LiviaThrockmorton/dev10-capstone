import { findDuck } from "../services/DuckService";
import { findItem } from "../services/ClothingItemService";
import { useEffect, useState } from "react";
import OutfitPlay from "./OutfitPlay";
import OutfitView from "./OutfitView";

function Outfit({ outfit, canDelete, viewOutfit, handleView, viewHome, profileView, loadOutfits }) {

    const [duck, setDuck] = useState([]);
    const [hat, setHat] = useState();
    const [shirt, setShirt] = useState();
    const [pants, setPants] = useState();
    const [error, setError] = useState(false);

    useEffect(() => {
        if (outfit.duckId) {
            findDuck(outfit.duckId)
                .then(setDuck)
                .catch(() => setError(true));
            console.log(outfit.duckId);
        }
    }, [outfit.duckId]);

    useEffect(() => {
        if (outfit.hatId) {
            findItem(outfit.hatId)
                .then(setHat)
                .catch(() => setError(true));
        }
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

    return (
        <>
            {viewOutfit ? <OutfitView duck={duck} hat={hat} shirt={shirt} pants={pants}
                outfit={outfit} canDelete={canDelete} error={error}
                handleView={handleView} profileView={profileView} loadOutfits={loadOutfits} />

                : <OutfitPlay duck={duck} hat={hat} shirt={shirt} pants={pants}
                    outfit={outfit} error={error} viewHome={viewHome} />
            }
        </>
    );
}

export default Outfit;


