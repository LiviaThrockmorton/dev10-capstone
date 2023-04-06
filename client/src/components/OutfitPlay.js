function OutfitPlay({duck, hat, shirt, pants, outfit, error, viewHome}) {

    return (
        <div className="mb-2 d-flex flex-wrap justify-content-between flex-row">
            {viewHome ? <div className="d-flex flex-wrap" >
                <img src={duck.duckImage} alt="duck" style={{ height: "150px", position: "relative", padding: "2px"}} />
                {hat && <img src={hat.clothingItemImage} alt="hat" style={{ height: "150px", position: "absolute" }} />}
                {pants && <img src={pants.clothingItemImage} alt="pants" style={{ height: "150px", position: "absolute" }} />}
                {shirt && <img src={shirt.clothingItemImage} alt="shirt" style={{ height: "150px", position: "absolute" }} />}
            </div> 
            :                   
            <div>
                <img src={duck.duckImage} className="duck" alt="duck" style={{ height: "800px", position: "absolute" }} />
                {hat && <img src={hat.clothingItemImage} alt="hat" style={{ height: "800px", position: "absolute" }} />}
                {pants && <img src={pants.clothingItemImage} alt="pants" style={{ height: "800px", position: "absolute" }} />}
                {shirt && <img src={shirt.clothingItemImage} alt="shirt" style={{ height: "800px", position: "absolute" }} />}
            </div>
}
            <div>{error && <p className="col mt-4 text-danger d-none">This duck doesn't want to wear clothes</p>}</div>
            <div className="d-none">{outfit.outfitId}</div>
            <div className="d-none">{outfit.dateCreated}</div>
            <div className="d-none">{outfit.posted}</div>
            <div className="d-none">{outfit.hidden}</div>
        </div>
    );
}


export default OutfitPlay;